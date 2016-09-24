package io.jdash;

import io.jdash.bean.JBeanWrapper;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

public class J {
    
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof Iterable) {
            return Iterables.isEmpty((Iterable) value);
        } else if (value instanceof String) {
            return StringUtils.isEmpty((String) value);
        } else {
            return false;
        }
    }
    
    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }
    
    public static <T> List<T> asList(T... values) {
        return Arrays.asList(values);
    }
    
    public static <T> Set<T> asSet(T... values) {
        return Sets.newHashSet(values);
    }
    
    //
    // Beans
    //
    
    public static Object get(Object bean, String propertyName) {
        return wrap(bean).get(propertyName);
    }
    
    public static void set(Object bean, String propertyName, Object value) {
        wrap(bean).set(propertyName, value);
    }

    public static <T> T instantiate(Class<T> beanClass) {
        return BeanUtils.instantiateClass(beanClass);
    }

    public static <T> JBeanWrapper<T> wrap(T bean) {
        return new JBeanWrapper<T>(bean);
    }
    
    public static <T> JBeanWrapper<T> wrap(Class<T> beanClass) {
        T bean = instantiate(beanClass);
        return new JBeanWrapper<T>(bean);
    }

}
