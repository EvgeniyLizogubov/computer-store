package com.github.evgenylizogubov.computerstore.repository;

import com.github.evgenylizogubov.computerstore.model.HardDrive;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface HardDriveRepository extends BaseRepository<HardDrive> {
}
