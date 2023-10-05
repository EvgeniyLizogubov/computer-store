package com.github.evgenylizogubov.computerstore.web;

import com.github.evgenylizogubov.computerstore.model.Monitor;
import com.github.evgenylizogubov.computerstore.repository.MonitorRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/monitor", produces = MediaType.APPLICATION_JSON_VALUE)
public class MonitorController extends AbstractController<Monitor, MonitorRepository> {
    protected MonitorController(MonitorRepository repository) {
        super(repository);
    }
}
