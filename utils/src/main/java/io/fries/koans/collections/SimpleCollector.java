package io.fries.koans.collections;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class SimpleCollector<T, A, R> implements Collector<T, A, R> {

    private final Supplier<A> supplier;
    private final BiConsumer<A, T> accumulator;
    private final BinaryOperator<A> combiner;
    private final Function<A, R> finisher;
    private final Set<Characteristics> characteristics;

    public SimpleCollector(
            final Supplier<A> supplier,
            final BiConsumer<A, T> accumulator,
            final BinaryOperator<A> combiner,
            final Function<A, R> finisher,
            final Set<Characteristics> characteristics
    ) {
        this.supplier = supplier;
        this.accumulator = accumulator;
        this.combiner = combiner;
        this.finisher = finisher;
        this.characteristics = characteristics;
    }

    @Override
    public Supplier<A> supplier() {
        return supplier;
    }

    @Override
    public BiConsumer<A, T> accumulator() {
        return accumulator;
    }

    @Override
    public BinaryOperator<A> combiner() {
        return combiner;
    }

    @Override
    public Function<A, R> finisher() {
        return finisher;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }
}
