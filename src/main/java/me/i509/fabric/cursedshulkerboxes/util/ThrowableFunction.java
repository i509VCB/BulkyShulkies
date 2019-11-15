package me.i509.fabric.cursedshulkerboxes.util;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface ThrowableFunction<T, R, X extends Throwable> {
    R apply(T t) throws X;

    default <V> ThrowableFunction<V, R, X> compose(ThrowableFunction<? super V, ? extends T, ? extends X> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    default <V> ThrowableFunction<T, V, X> andThen(ThrowableFunction<? super R, ? extends V, ? extends X> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    static <T, X extends Throwable> ThrowableFunction<T, T, X> identity() throws X {
        return t -> t;
    }
}
