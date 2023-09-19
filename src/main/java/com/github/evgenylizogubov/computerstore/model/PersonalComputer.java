package com.github.evgenylizogubov.computerstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "personal_computer")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class PersonalComputer extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "form_factor", nullable = false)
    @NotNull
    private FormFactor formFactor;
}
