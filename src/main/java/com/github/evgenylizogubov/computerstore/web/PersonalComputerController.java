package com.github.evgenylizogubov.computerstore.web;

import com.github.evgenylizogubov.computerstore.model.PersonalComputer;
import com.github.evgenylizogubov.computerstore.repository.PersonalComputerRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.github.evgenylizogubov.computerstore.util.validation.ValidationUtil.assureIdConsistent;
import static com.github.evgenylizogubov.computerstore.util.validation.ValidationUtil.checkNew;

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
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalComputer> createWithLocation(@Valid @RequestBody PersonalComputer personalComputer) {
        log.info("createWithLocation {}", personalComputer);
        checkNew(personalComputer);
        PersonalComputer created = repository.save(personalComputer);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/personal-computer/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
    
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody PersonalComputer personalComputer, @PathVariable int id) {
        log.info("update {} with id={}", personalComputer, id);
        assureIdConsistent(personalComputer, id);
        repository.save(personalComputer);
    }
}
