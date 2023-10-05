package com.github.evgenylizogubov.computerstore.repository;

import com.github.evgenylizogubov.computerstore.model.Laptop;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface LaptopRepository extends BaseRepository<Laptop> {
}
