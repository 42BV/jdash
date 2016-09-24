package io.jdash.bean;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class JBeanWrapper<T> {
    
    private final BeanWrapper wrapper;
    
    public JBeanWrapper(T bean) {
        this.wrapper = new BeanWrapperImpl(bean);
    }
    
    public Object get(String propertyName) {
        return wrapper.getPropertyValue(propertyName);
    }
    
    public JBeanWrapper<T> set(String propertyName, Object value) {
        wrapper.setPropertyValue(propertyName, value);
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public T value() {
        return (T) wrapper.getWrappedInstance();
    }

}
