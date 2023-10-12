package com.github.evgenylizogubov.computerstore.web;

import com.github.evgenylizogubov.computerstore.config.CachingConfiguration;
import com.github.evgenylizogubov.computerstore.model.BaseEntity;
import com.github.evgenylizogubov.computerstore.repository.BaseRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ResolvableType;
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
    private final ResolvableType resolvableType = ResolvableType.forClass(this.getClass()).as(AbstractController.class);
    private final String entityTypeName = resolvableType.getGeneric(0).toClass().getSimpleName();
    private final S repository;
    
    protected AbstractController(S repository) {
        this.repository = repository;
    }
    
    @GetMapping("/{id}")
    @Cacheable(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME)
    public E get(@PathVariable int id) {
        log.info("{}: get id = {}", entityTypeName, id);
        return repository.getExisted(id);
    }
    
    @GetMapping
    @Cacheable(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME)
    public List<E> getAll() {
        log.info("{}: getAll", entityTypeName);
        return repository.findAll(Sort.by("seriesNumber"));
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME, allEntries = true)
    public ResponseEntity<E> createWithLocation(@Valid @RequestBody E entity) {
        log.info("{}: createWithLocation {}", entityTypeName, entity);
        checkNew(entity);
        E created = repository.save(entity);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/personal-computer/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
    
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME, allEntries = true)
    public void update(@Valid @RequestBody E entity, @PathVariable int id) {
        log.info("{}: update {} with id={}", this.getClass().getSimpleName(), entity, id);
        assureIdConsistent(entity, id);
        repository.save(entity);
    }
}
