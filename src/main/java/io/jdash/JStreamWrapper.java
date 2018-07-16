package io.jdash;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
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
    
    public <V extends Comparable<? super V>> JStreamWrapper<T> sort() {
        stream = stream.sorted();
        return this;
    }

    public <V extends Comparable<? super V>> JStreamWrapper<T> sort(Function<T, V> function) {
        stream = stream.sorted(Comparator.comparing(function, Comparator.nullsFirst(Comparator.naturalOrder())));
        return this;
    }

    public JStreamWrapper<T> filter(Predicate<T> predicate) {
        stream = stream.filter(predicate);
        return this;
    }

    /**
     * Filters the stream using a predicate function, and then runs a function on the filtered result set.
     * @param predicate Filter expression
     * @param processor Function to run *after* the results have been filtered
     * @return Stream containing the filtered results (new instance)
     */
    public JStreamWrapper<T> filterAndDo(Predicate<T> predicate, Consumer<List<T>> processor) {
        List<T> filteredResults = stream.filter(predicate).collect(Collectors.toList());

        if (processor != null) {
            processor.accept(filteredResults);
        }

        stream = filteredResults.stream();
        return this;
    }
    
    public <V> JStreamWrapper<V> map(Function<T, V> mapper) {
        Stream<V> result = stream.map(mapper);
        return new JStreamWrapper<>(result);
    }
    
    public <V> JStreamWrapper<V> pick(String propertyName) {
        return map(bean -> (V) J.get(bean, propertyName));
    }
    
    public <V> JStreamWrapper<V> pick(String propertyName, Class<V> propertyType) {
        return this.pick(propertyName);
    }

    public Optional<T> find() {
        return stream.findFirst();
    }
    
    public T value() {
        return find().orElseThrow(() -> new NullPointerException("Expected at least one value."));
    }
    
    public T value(T defaultValue) {
        return find().orElse(defaultValue);
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
