package com.github.evgenylizogubov.computerstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "monitor")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Monitor extends BaseEntity {
    @Column(name = "screen_size", nullable = false)
    @Min(0)
    private int screenSize;
}
