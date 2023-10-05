package com.github.evgenylizogubov.computerstore.repository;

import com.github.evgenylizogubov.computerstore.model.Monitor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MonitorRepository extends BaseRepository<Monitor> {
}
