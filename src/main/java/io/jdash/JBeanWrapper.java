package io.jdash;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

@SuppressWarnings("unchecked")
public class JBeanWrapper<T> {
    
    private final BeanWrapper wrapper;
    
    public JBeanWrapper(T bean) {
        this.wrapper = new BeanWrapperImpl(bean);
    }
    
    public <V> Object get(String propertyName) {
        return (V) wrapper.getPropertyValue(propertyName);
    }
    
    public <V> Object get(String propertyName, Class<V> valueType) {
        return get(propertyName);
    }

    public JBeanWrapper<T> set(String propertyName, Object value) {
        wrapper.setPropertyValue(propertyName, value);
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public T instance() {
        return (T) wrapper.getWrappedInstance();
    }

}
