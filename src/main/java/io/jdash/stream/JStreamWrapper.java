package io.jdash.stream;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JStreamWrapper<T> {
    
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
    
    public Optional<T> first() {
        return stream.findFirst();
    }
    
    public T value() {
        return first().orElseThrow(() -> new NullPointerException("Expected atleast one value."));
    }

    public List<T> asList() {
        return stream.collect(Collectors.<T> toList());
    }
    
    public Set<T> asSet() {
        return stream.collect(Collectors.<T> toSet());
    }

}
