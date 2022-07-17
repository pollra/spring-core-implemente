package com.pollra.spring.framework.context.register;

import com.pollra.spring.framework.beans.definition.BeanDefinition;
import com.pollra.spring.framework.beans.factory.BeansException;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @since       2022.07.10
 * @author      pollra
 * @description in memory bean register
 **********************************************************************************************************************/
@ToString
public class InMemoryBeanDefinitionRegister implements BeanDefinitionRegister {

    private final Map<String, BeanDefinition> beanStore;

    public InMemoryBeanDefinitionRegister() {
        beanStore = new HashMap<>();
    }

    @Override
    public void add(String beanName, BeanDefinition beanDefinition) {
        if(beanName == null || beanName.isEmpty()) {
            String beanClassName = beanDefinition.getBeanClassName();
            beanStore.put(beanClassName, beanDefinition);
            System.out.println("beanStore = " + beanStore);
        }else {
            String beanClassName = beanDefinition.getBeanClassName();
            try {
                Class aClass = Class.forName(beanClassName);
                beanName = aClass.getSimpleName();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            beanStore.put(beanName, beanDefinition);
            System.out.println("beanStore = " + beanStore);
        }
    }

    @Override
    public BeanDefinition get(String beanName) {
        return beanStore.get(beanName);
    }

    public boolean isContains(String beanName) {
        if(beanName == null || beanName.isEmpty()) {
            throw new BeansException("contains query is null");
        }
        return beanStore.containsKey(beanName);
    }

    @Override
    public boolean isContains(Class<?> type) {
        for (Map.Entry<String, BeanDefinition> definitionEntry : beanStore.entrySet()) {
            BeanDefinition value = definitionEntry.getValue();
            String beanClassName = value.getBeanClassName();
            if(Objects.requireNonNull(beanClassName).equals(type.getSimpleName())) {
                return true;
            }
        }
        return false;
    }

    public BeanDefinition get(Class<?> type) {
        for (Map.Entry<String, BeanDefinition> definitionEntry : beanStore.entrySet()) {
            BeanDefinition value = definitionEntry.getValue();
            String beanClassName = value.getBeanClassName();
            if(Objects.requireNonNull(beanClassName).equals(type.getSimpleName())) {
                return value;
            }
        }
        if(isContains(type.getTypeName())) {
            return get(type.getTypeName());
        }
        throw new BeansException("bean definition is not found");
    }
}
