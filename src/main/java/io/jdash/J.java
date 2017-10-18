package io.jdash;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Java dash, optimizes often used java functionality.
 */
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

    public static <T> JBeanWrapper<T> bean(Class<T> beanClass) {
        T bean = instantiate(beanClass);
        return wrap(bean);
    }
    
    public static <T> JBeanWrapper<T> wrap(T bean) {
        return new JBeanWrapper<T>(bean);
    }
    
    //
    // Streams and collections
    //

    public static <T> JStreamWrapper<T> stream(Iterable<T> values) {
        Stream<T> stream = asStream(values);
        return wrap(stream);
    }
    
    public static <T> JStreamWrapper<T> stream(T... values) {
        Stream<T> stream = asStream(values);
        return wrap(stream);
    }

    public static <T> JStreamWrapper<T> wrap(Stream<T> stream) {
        return new JStreamWrapper<>(stream);
    }

    public static <T> Stream<T> asStream(T... values) {
        return asList(values).stream();
    }
    
    public static <T> Stream<T> asStream(Iterable<T> values) {
        return asList(values).stream();
    }

    // Filtering
    
    public static <T> T first(Iterable<T> values) {
        return stream(values).value();
    }
    
    public static <T> T first(Iterable<T> values, T defaultValue) {
        return stream(values).value(defaultValue);
    }

    public static <T> Optional<T> findOptional(Stream<T> stream, Predicate<T> filter) {
        return wrap(stream).filter(filter).find();
    }
    
    public static <T> Optional<T> findOptional(Iterable<T> values, Predicate<T> filter) {
        Stream<T> stream = asStream(values);
        return findOptional(stream, filter);
    }

    public static <T> T find(Stream<T> stream, Predicate<T> filter) {
        return wrap(stream).filter(filter).value();
    }
    
    public static <T> T find(Stream<T> stream, Predicate<T> filter, T defaultValue) {
        return wrap(stream).filter(filter).value(defaultValue);
    }
    
    public static <T> T find(Iterable<T> values, Predicate<T> filter) {
        Stream<T> stream = asStream(values);
        return find(stream, filter);
    }
    
    public static <T> T find(Iterable<T> values, Predicate<T> filter, T defaultValue) {
        Stream<T> stream = asStream(values);
        return find(stream, filter, defaultValue);
    }

    public static <T> T findNotNull(T... values) {
        Stream<T> stream = asStream(values);
        return findNotNull(stream);
    }
    
    public static <T> T findNotNull(Iterable<T> values) {
        Stream<T> stream = asStream(values);
        return findNotNull(stream);
    }
    
    public static <T> T findNotNull(Stream<T> stream) {
        return findOptional(stream, value -> value != null).orElseThrow(() -> new NullPointerException("Expected atleast one not null value."));
    }

    public static <T> int count(Iterable<T> values, Predicate<T> predicate) {
        Stream<T> stream = asStream(values);
        return count(stream, predicate);
    }
    
    public static <T> int count(Stream<T> stream, Predicate<T> predicate) {
        return filter(stream, predicate).size();
    }
    
    public static <T> boolean all(Iterable<T> values, Predicate<T> predicate) {
        Stream<T> stream = asStream(values);
        return stream.allMatch(predicate);
    }
    
    public static <T> boolean any(Iterable<T> values, Predicate<T> predicate) {
        Stream<T> stream = asStream(values);
        return stream.anyMatch(predicate);
    }
    
    public static <T> boolean none(Iterable<T> values, Predicate<T> predicate) {
        Stream<T> stream = asStream(values);
        return stream.noneMatch(predicate);
    }

    public static <T> List<T> filter(Iterable<T> values, Predicate<T> predicate) {
        Stream<T> stream = asStream(values);
        return filter(stream, predicate);
    }
    
    public static <T> List<T> filter(Stream<T> stream, Predicate<T> predicate) {
        return stream.filter(predicate).collect(Collectors.toList());
    }

    public static <T> List<T> without(Iterable<T> values, Predicate<T> predicate) {
        Stream<T> stream = asStream(values);
        return without(stream, predicate);
    }
    
    public static <T> List<T> without(Stream<T> stream, Predicate<T> predicate) {
        return filter(stream, inverse(predicate));
    }
    
    public static <T> Predicate<T> inverse(Predicate<T> predicate) {
        return (x) -> !predicate.test(x);
    }

    // Mapping
    
    public static <T, V> List<V> map(Iterable<T> values, Function<T, V> function) {
        return mapStream(values, function).asList();
    }
    
    public static <T, V> Set<V> mapUnique(Iterable<T> values, Function<T, V> function) {
        return mapStream(values, function).asSet();
    }
    
    public static <T, V> JStreamWrapper<V> mapStream(Iterable<T> values, Function<T, V> function) {
        return stream(values).map(function);
    }

    // Sorting
    
    public static <T> List<T> sort(Iterable<T> values) {
        return stream(values).sort().asList();
    }

    public static <T, V extends Comparable<? super V>> List<T> sort(Iterable<T> values, Function<T, V> function) {
        return stream(values).sort(function).asList();
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
    
    public static <T> void each(Iterable<T> values, Consumer<T> consumer) {
        stream(values).forEach(consumer);
    }
    
    public static int findIndex(Iterable<?> values, Object value) {
        List<?> collection = asList(values);
        return collection.indexOf(value);
    }
    
    public static <T> Collection<T> without(Iterable<T> values, T... exclusions) {
        List<T> result = asList(values);
        each(asList(exclusions), exclusion -> result.remove(exclusion));
        return result;
    }
    
    public static String join(Iterable<?> values, String separator) {
        List<?> collection = asList(values);
        return StringUtils.collectionToDelimitedString(collection, separator);
    }
    
    public static String join(Iterable<?> values) {
        return join(values, ", ");
    }
    
    //
    // Dates
    //

    public static LocalDate today() {
        return LocalDate.now();
    }
    
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
    
    //
    // Strings
    //
    
    public static String defaultString(String value, String defaultValue) {
        if (isEmpty(value)) {
            value = defaultValue;
        }
        return value;
    }
    
    public static String defaultString(String value) {
        return defaultString(value, "");
    }
    
    public static String trim(String value) {
        return defaultString(value, "").trim();
    }
    
    public static String lowerCase(String value) {
        return defaultString(value, "").toLowerCase();
    }
    
    public static String upperCase(String value) {
        return defaultString(value, "").toUpperCase();
    }

    //
    // Preconditions
    //
    
    public static void check(boolean expectation, Supplier<? extends RuntimeException> supplier) {
        if (!expectation) {
            throw supplier.get();
        }
    }
    
    public static void checkArgument(boolean expectation, String message) {
        check(expectation, () -> new IllegalArgumentException(message));
    }

    public static void checkState(boolean expectation, String message) {
        check(expectation, () -> new IllegalStateException(message));
    }

    public static void checkNotNull(Object value, String message) {
        check(value != null, () -> new NullPointerException(message));
    }

}
