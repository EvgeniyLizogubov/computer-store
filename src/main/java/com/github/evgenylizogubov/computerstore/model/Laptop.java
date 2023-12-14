package com.github.evgenylizogubov.computerstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.evgenylizogubov.computerstore.error.IllegalRequestDataException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "laptop")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Laptop extends BaseEntity {
    @Transient
    @JsonIgnore
    private final Set<Integer> sizes = new HashSet<>(Arrays.asList(13, 14, 15, 17));
    
    @Column(name = "size")
    private int size;
    
    public void setSize(int size) {
        if (sizes.contains(size)) {
            this.size = size;
        } else {
            throw new IllegalRequestDataException("Invalid laptop size");
        }
    }
}
