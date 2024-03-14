package com.organizamoney.backend.springmonolith.domain.shared.logic;

import java.util.Objects;

public class Either<L, R> {
    private final L left;
    private final R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Either<L, R> left(L value) {
        return new Either<>(Objects.requireNonNull(value), null);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Either<>(null, Objects.requireNonNull(value));
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }
}
