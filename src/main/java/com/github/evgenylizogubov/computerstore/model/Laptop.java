package com.github.evgenylizogubov.computerstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "laptop")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Laptop extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    @NotNull
    private LaptopSize size;
}
