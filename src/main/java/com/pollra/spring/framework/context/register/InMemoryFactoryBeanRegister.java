package com.pollra.spring.framework.context.register;

import com.pollra.spring.framework.beans.factory.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @since       2022.07.10
 * @author      pollra
 * @description in memory factory register
 **********************************************************************************************************************/
public class InMemoryFactoryBeanRegister implements FactoryBeanRegister {

    private final Map<String, FactoryBean> factoryStore;

    public InMemoryFactoryBeanRegister() {
        factoryStore = new HashMap<>();
        factoryStore.put("SingleModelFactoryBean", new SingleModelFactoryBean<>());
        factoryStore.put("MethodFactoryBean", new MethodFactoryBean<>());
        factoryStore.put("PrimaryFactoryBean", new PrimaryFactoryBean());
    }

    @Override
    public FactoryBean get(String factoryBeanName) {
        if(factoryStore.containsKey(factoryBeanName)) {
            return factoryStore.get(factoryBeanName);
        }
        throw new BeansException(factoryBeanName+" factory not found");
    }

    @Override
    public void add(FactoryBean factoryBean) {
        factoryStore.put(factoryBean.getFactoryName(), factoryBean);
    }
}
