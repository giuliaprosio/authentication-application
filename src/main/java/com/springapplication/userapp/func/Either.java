package com.springapplication.userapp.func;


import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * A sealed interface to allow to implement ADT Either type, a data type
 * composed of two disjoint values, a Left one and a Right one.
 * @param <L> error object
 * @param <R> success object
 */

public sealed interface Either<L, R> permits Either.Left, Either.Right {

    boolean isLeft();

    boolean isRight();

    R right();

    L left();

    static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }

    static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }

    default <T> T fold(Function<? super L, ? extends T> leftFunc, Function<? super R, ? extends T> rightFunc) {
        return isLeft() ? leftFunc.apply(left()) : rightFunc.apply(right());
    }

    default <U> Either<L, U> map(Function<? super R, ? extends U> mapper) {
        return fold(Either::left, r -> Either.right(mapper.apply(r)));
    }


    default <U> Either<U, R> mapLeft(Function<? super L, ? extends U> mapper) {
        return fold(l -> Either.left(mapper.apply(l)), Either::right);
    }

    default <U> Either<L, U> flatMap(Function<? super R, Either<L, U>> mapper) {
        return fold(Either::left, mapper);
    }

    record Left<L, R>(L value) implements Either<L, R> {

        @Override
        public boolean isLeft() {
            return true;
        }

        @Override
        public boolean isRight() {
            return false;
        }

        @Override
        public R right() {
            throw new NoSuchElementException("getRight() on Left");
        }

        @Override
        public L left() {
            return value;
        }

        @Override
        public String toString() {
            return "Left" + "(" + value + ")";
        }

    }

    record Right<L, R>(R value) implements Either<L, R> {

        @Override
        public boolean isLeft() {
            return false;
        }

        @Override
        public boolean isRight() {
            return true;
        }

        @Override
        public R right() {
            return value;
        }

        @Override
        public L left() {
            throw new NoSuchElementException("getRight() on Left");
        }

        @Override
        public String toString() {
            return "Right" + "(" + value + ")";
        }
    }

}
