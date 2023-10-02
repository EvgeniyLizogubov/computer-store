package com.github.evgenylizogubov.computerstore.error;

import jakarta.validation.constraints.NotNull;

public class AppException extends RuntimeException {
    public AppException(@NotNull String message) {
        super(message);
    }
}
