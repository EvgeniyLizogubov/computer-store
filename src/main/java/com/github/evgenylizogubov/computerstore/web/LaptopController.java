package com.github.evgenylizogubov.computerstore.web;

import com.github.evgenylizogubov.computerstore.model.Laptop;
import com.github.evgenylizogubov.computerstore.repository.LaptopRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/laptop", produces = MediaType.APPLICATION_JSON_VALUE)
public class LaptopController extends AbstractController<Laptop, LaptopRepository> {
    protected LaptopController(LaptopRepository repository) {
        super(repository);
    }
}
