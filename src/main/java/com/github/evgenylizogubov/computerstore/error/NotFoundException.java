package com.github.evgenylizogubov.computerstore.error;

import jakarta.validation.constraints.NotNull;

public class NotFoundException extends RuntimeException {
    public NotFoundException(@NotNull String message) {
        super(message);
    }
}
