package com.github.evgenylizogubov.computerstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "hard_drive")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class HardDrive extends BaseEntity {
    @Column(name = "capacity", nullable = false)
    @Min(0)
    private int capacity;
}
