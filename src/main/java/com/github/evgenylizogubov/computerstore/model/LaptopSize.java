package com.github.evgenylizogubov.computerstore.model;

import lombok.Getter;

@Getter
public enum LaptopSize {
    thirteen(13),
    fourteen(14),
    fifteen(15),
    seventeen(17);
    
    private final int size;
    
    LaptopSize(int size) {
        this.size = size;
    }
}
