package io.jdash;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class JStreamWrapper<T> implements Iterable<T> {
    
    private Stream<T> stream;
    
    public JStreamWrapper(Stream<T> stream) {
        this.stream = stream;
    }
    
    public Stream<T> stream() {
        return stream;
    }
    
    public JStreamWrapper<T> filter(Predicate<T> predicate) {
        stream = stream.filter(predicate);
        return this;
    }
    
    public <V> JStreamWrapper<V> map(Function<T, V> mapper) {
        Stream<V> result = stream.map(mapper);
        return new JStreamWrapper<>(result);
    }
    
    public <V> JStreamWrapper<V> pick(String propertyName) {
        return map(bean -> (V) J.get(bean, propertyName));
    }

    public Optional<T> first() {
        return stream.findFirst();
    }
    
    public T value() {
        return first().orElseThrow(() -> new NullPointerException("Expected atleast one value."));
    }
    
    public int size() {
        return asList().size();
    }
    
    public boolean isEmpty() {
        return asList().isEmpty();
    }
    
    public Iterator<T> iterator() {
        return asList().iterator();
    }

    public List<T> asList() {
        return stream.collect(Collectors.<T> toList());
    }
    
    public Set<T> asSet() {
        return stream.collect(Collectors.<T> toSet());
    }

}
