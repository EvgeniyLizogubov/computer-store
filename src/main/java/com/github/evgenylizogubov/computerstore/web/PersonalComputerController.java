package com.github.evgenylizogubov.computerstore.web;

import com.github.evgenylizogubov.computerstore.model.PersonalComputer;
import com.github.evgenylizogubov.computerstore.repository.PersonalComputerRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/personal-computer", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonalComputerController extends AbstractController<PersonalComputer, PersonalComputerRepository> {
    protected PersonalComputerController(PersonalComputerRepository repository) {
        super(repository);
    }
}
