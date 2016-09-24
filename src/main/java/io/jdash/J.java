package io.jdash;

import io.jdash.bean.JBeanWrapper;
import io.jdash.stream.JStreamWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

@SuppressWarnings("unchecked")
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

    //
    // Beans
    //
    
    public static <V> Object get(Object bean, String propertyName) {
        return wrap(bean).get(propertyName);
    }
    
    public static <V> Object get(Object bean, String propertyName, Class<V> valueType) {
        return get(bean, propertyName);
    }

    public static void set(Object bean, String propertyName, Object value) {
        wrap(bean).set(propertyName, value);
    }

    public static <T> T instantiate(Class<T> beanClass) {
        return BeanUtils.instantiateClass(beanClass);
    }

    public static <T> JBeanWrapper<T> wrap(Class<T> beanClass) {
        T bean = instantiate(beanClass);
        return wrap(bean);
    }
    
    public static <T> JBeanWrapper<T> wrap(T bean) {
        return new JBeanWrapper<T>(bean);
    }
    
    //
    // Streams and collections
    //

    public static <T> JStreamWrapper<T> wrap(Stream<T> stream) {
        return new JStreamWrapper<>(stream);
    }
    
    public static <T> JStreamWrapper<T> wrap(Iterable<T> values) {
        Stream<T> stream = stream(values);
        return wrap(stream);
    }
    
    public static <T> JStreamWrapper<T> wrap(T... values) {
        Stream<T> stream = stream(values);
        return wrap(stream);
    }

    public static <T> Stream<T> stream(T... values) {
        return asList(values).stream();
    }
    
    public static <T> Stream<T> stream(Iterable<T> values) {
        return asList(values).stream();
    }

    public static <T> Optional<T> find(Stream<T> stream, Predicate<T> filter) {
        return wrap(stream).filter(filter).first();
    }
    
    public static <T> Optional<T> find(Iterable<T> values, Predicate<T> filter) {
        Stream<T> stream = stream(values);
        return find(stream, filter);
    }

    public static <T> T first(Stream<T> stream, Predicate<T> filter) {
        return wrap(stream).filter(filter).value();
    }
    
    public static <T> T first(Iterable<T> values, Predicate<T> filter) {
        Stream<T> stream = stream(values);
        return first(stream, filter);
    }

    public static <T> T firstNotNull(T... values) {
        Stream<T> stream = stream(values);
        return firstNotNull(stream);
    }
    
    public static <T> T firstNotNull(Iterable<T> values) {
        Stream<T> stream = stream(values);
        return firstNotNull(stream);
    }
    
    public static <T> T firstNotNull(Stream<T> stream) {
        return find(stream, value -> value != null).orElseThrow(() -> new NullPointerException("Expected atleast one not null value."));
    }

    public static <T> int count(Iterable<T> values, Predicate<T> predicate) {
        Stream<T> stream = stream(values);
        return count(stream, predicate);
    }
    
    public static <T> int count(Stream<T> stream, Predicate<T> predicate) {
        return wrap(stream).filter(predicate).size();
    }

    //
    // Collections
    //
    
    public static <T> List<T> asList(Iterable<T> values) {
        List<T> result = new ArrayList<>();
        if (values != null) {
            values.forEach(value -> result.add(value));
        }
        return result;
    }
    
    public static <T> List<T> asList(T... values) {
        return Arrays.asList(values);
    }
    
    public static <T> Set<T> asSet(T... values) {
        return Sets.newHashSet(values);
    }

    public static <T> Collection<T> emptyCollection() {
        return Collections.emptyList();
    }
    
    public static String join(Iterable<?> values, String separator) {
        List<?> collection = asList(values);
        return StringUtils.collectionToDelimitedString(collection, separator);
    }
    
    public static String join(Iterable<?> values) {
        return join(values, ", ");
    }

}
