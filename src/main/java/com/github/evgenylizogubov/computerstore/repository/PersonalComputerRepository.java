package com.github.evgenylizogubov.computerstore.repository;

import com.github.evgenylizogubov.computerstore.model.PersonalComputer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PersonalComputerRepository extends BaseRepository<PersonalComputer> {
}
