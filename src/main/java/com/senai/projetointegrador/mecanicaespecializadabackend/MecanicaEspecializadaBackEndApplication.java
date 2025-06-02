package com.senai.projetointegrador.mecanicaespecializadabackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "${info.build.name}", version = "${info.build.version}", description = "${info.app.description}",
        contact = @io.swagger.v3.oas.annotations.info.Contact(name = "Time Arquitetura e APIs", email = "")))


@SpringBootApplication
public class MecanicaEspecializadaBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(MecanicaEspecializadaBackEndApplication.class, args);
    }

}
