package com.github.evgenylizogubov.computerstore.web;

import com.github.evgenylizogubov.computerstore.model.BaseEntity;
import com.github.evgenylizogubov.computerstore.repository.BaseRepository;
import jakarta.validation.Valid;
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

@Slf4j
public abstract class AbstractController<E extends BaseEntity, S extends BaseRepository<E>> {
    private final S repository;
    
    protected AbstractController(S repository) {
        this.repository = repository;
    }
    
    @GetMapping("/{id}")
    public E get(@PathVariable int id) {
        log.info("get id = {}", id);
        return repository.getExisted(id);
    }
    
    @GetMapping
    public List<E> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by("seriesNumber"));
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<E> createWithLocation(@Valid @RequestBody E entity) {
        log.info("createWithLocation {}", entity);
        checkNew(entity);
        E created = repository.save(entity);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/personal-computer/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
    
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody E entity, @PathVariable int id) {
        log.info("update {} with id={}", entity, id);
        assureIdConsistent(entity, id);
        repository.save(entity);
    }
}
