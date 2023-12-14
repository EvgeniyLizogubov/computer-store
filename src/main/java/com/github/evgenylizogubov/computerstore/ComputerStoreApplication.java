package com.github.evgenylizogubov.computerstore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        servers = {@Server(url = "/", description = "Default Server URL")},
        info = @Info(
                title = "REST Api documentation",
                description = "Реализация REST Api приложения для магазина, торгующего компьютерами и комплектующими."
        )
)
public class ComputerStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComputerStoreApplication.class, args);
    }
    
}
