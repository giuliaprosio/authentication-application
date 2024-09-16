package com.springapplication.userapp.model;

import java.util.function.Function;

public sealed interface Result<T, E> permits Result.Success, Result.Failure {

    record Success<T, E>(T value) implements Result<T, E> {}

    record Failure<T, E>(E error) implements Result<T, E> {}

    static <T, E> Result<T, E> success(T value) {
        return new Success<>(value);
    }

    static <T, E> Result<T, E> failure(E error) {
        return new Failure<>(error);
    }

    default <U> Result<U, E> map(Function<? super T, ? extends U> mapper) {
        if(this instanceof Success<T, E> success) {
            return success(mapper.apply(success.value));
        }
        return (Failure<U,E>) this;
    }

    default <U> U fold(Function<? super T, ? extends U> successMapper, Function<? super E, ? extends U> errorMapper) {
        if(this instanceof Success<T, E> success) {
            return successMapper.apply(success.value);
        }
        if(this instanceof Failure<T, E> failure) {
            return errorMapper.apply(failure.error);
        }
        throw new IllegalStateException("Unknown result state");
    }
}
