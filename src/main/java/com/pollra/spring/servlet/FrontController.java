package com.pollra.spring.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pollra.spring.Application;
import com.pollra.spring.context.BeanFactory;
import com.pollra.spring.core.scanner.BeanScanner;
import com.pollra.spring.servlet.anno.*;
import com.pollra.spring.servlet.definition.HandlerDefinition;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @since       2022.07.28
 * @author      pollra
 * @description front controller
 **********************************************************************************************************************/
public class FrontController extends SessionHandler {

    private BeanFactory beanFactory;
    private RequestProcessingFactory requestProcessingFactory;

    public FrontController(BeanFactory beanFactory) {

        this.beanFactory = beanFactory;
        // 핸들러 매핑 정보 스캔
        Set<Class<?>> controllerClasses = BeanScanner.scan(RestController.class, Application.class);

        // 핸들러 어댑터 목록 저장
        this.requestProcessingFactory = new RequestProcessingFactory(controllerClasses, beanFactory);

        // 핸들러 어댑터에 연결

    }

    public void doDispatch(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // request uri 를 기준으로 처리 instance 와 처리 method 를 찾는다
        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();
        HandlerDefinition requestHandlerDefinition = new HandlerDefinition(httpMethod, requestURI);
        HandlerMethodDefinition processMethod = requestProcessingFactory.getProcessMethod(requestHandlerDefinition);
        ObjectMapper mapper = new ObjectMapper();
        Object[] arguments = null;
        String requestMethod = request.getMethod();
        if(HttpMethod.GET.name().equals(requestMethod)) {

            // uri 에 있는 값을 비교해서 method 추출

            // 추출된 메서드를 실행
        } else {
            ServletInputStream inputStream = request.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            String requestBody = new String(bytes);
            Class<?>[] parameterTypes = processMethod.getParameters();
            arguments = new Object[parameterTypes.length - 1];

            final int argumentIndex = 0;
            for (Class<?> parameterType : parameterTypes) {
                try {
                    Class<?> requestModel = Class.forName(parameterType.getName());
                    Object requestArgument = mapper.readValue(requestBody, requestModel);
                    arguments[argumentIndex] = requestArgument;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        Object invokeResult = processMethod.invoke(arguments);
        response.getWriter().write(mapper.writeValueAsString(invokeResult));
        // viewResolver 에서 처리

        // 처리 결과 리턴
    }

    @Override
    public void doHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        baseRequest.setHandled(true);
        doDispatch(target, baseRequest, request, response);
    }
}
