package com.github.evgenylizogubov.computerstore.web;

import com.github.evgenylizogubov.computerstore.model.HardDrive;
import com.github.evgenylizogubov.computerstore.repository.HardDriveRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/hard-drive", produces = MediaType.APPLICATION_JSON_VALUE)
public class HardDriveController extends AbstractController<HardDrive, HardDriveRepository> {
    protected HardDriveController(HardDriveRepository repository) {
        super(repository);
    }
}
