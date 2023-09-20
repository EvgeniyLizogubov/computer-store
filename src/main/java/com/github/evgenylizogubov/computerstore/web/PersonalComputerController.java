package com.github.evgenylizogubov.computerstore.web;

import com.github.evgenylizogubov.computerstore.model.PersonalComputer;
import com.github.evgenylizogubov.computerstore.repository.PersonalComputerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/personal-computer", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class PersonalComputerController {
    private final PersonalComputerRepository repository;
    
    @GetMapping("/{id}")
    public PersonalComputer get(@PathVariable int id) {
        log.info("get id = {}", id);
        return repository.getExisted(id);
    }
    
    @GetMapping
    public List<PersonalComputer> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by("formFactor"));
    }
}
