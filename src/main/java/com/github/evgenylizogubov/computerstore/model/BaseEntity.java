package com.github.evgenylizogubov.computerstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Persistable;
import org.springframework.data.util.ProxyUtils;
import org.springframework.util.Assert;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class BaseEntity implements Persistable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    protected Integer id;
    
    @Column(name = "series_number", unique = true, nullable = false)
    protected Integer seriesNumber;
    
    @Column(name = "manufacturer", nullable = false)
    @Size(min = 1, max = 128)
    @NotBlank
    protected String manufacturer;
    
    @Column(name = "price", nullable = false)
    @Min(1)
    @NotNull
    protected int price;
    
    @Column(name = "amount_in_stock", nullable = false)
    @Min(0)
    @NotNull
    protected Integer amountInStock;
    
    public int id() {
        Assert.notNull(id, "Entity must have id");
        return id;
    }
    
    @Override
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().equals(ProxyUtils.getUserClass(o))) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null && id.equals(that.id);
    }
    
    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}
