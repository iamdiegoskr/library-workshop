package com.sofka.training.biblioteca.services;

import com.sofka.training.biblioteca.collections.Resource;
import com.sofka.training.biblioteca.dtos.ResourceDTO;
import com.sofka.training.biblioteca.mappers.ResourceMapper;
import com.sofka.training.biblioteca.repositories.ResourceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ResourceServiceTest {

    @MockBean
    private ResourceRepository repository;

    @Autowired
    private ResourceService service;

    @Autowired
    private ResourceMapper mapper;


    private List<Resource> resources() {

        var resource = new Resource();
        resource.setId("1111");
        resource.setName("Don Quijote de la Mancha");
        resource.setKind("Novela");
        resource.setThematic("Historia");
        resource.setQuantityAvailable(30);
        resource.setAmountBorrowed(0);
        resource.setLocalDate(LocalDate.parse("2021-11-13"));

        var resource2 = new Resource();
        resource2.setId("2222");
        resource2.setName("Guerra y paz");
        resource2.setKind("Novela");
        resource2.setThematic("Historia");
        resource2.setQuantityAvailable(15);
        resource2.setAmountBorrowed(0);
        resource2.setLocalDate(LocalDate.parse("2021-11-13"));

        var resource3 = new Resource();
        resource3.setId("3333");
        resource3.setName("Orgullo y prejuicio");
        resource3.setKind("Novela");
        resource3.setThematic("Romance");
        resource3.setQuantityAvailable(15);
        resource3.setAmountBorrowed(0);
        resource3.setLocalDate(LocalDate.parse("2021-11-13"));

        var resources = new ArrayList<Resource>();
        resources.add(resource);
        resources.add(resource2);
        resources.add(resource3);

        return resources;
    }

    @Test
    @DisplayName("Test save resource success")
    void create() {

        var resource = new Resource();
        resource.setId("1111");
        resource.setName("Las aventuras de tom y dally");
        resource.setKind("Libro");
        resource.setThematic("Aventura");
        resource.setQuantityAvailable(100);
        resource.setAmountBorrowed(0);
        resource.setLocalDate(LocalDate.parse("2021-11-13"));

        var resourceDTO = new ResourceDTO();
        resourceDTO.setName("Las aventuras de tom y dally");
        resourceDTO.setKind("Libro");
        resourceDTO.setThematic("Aventura");
        resourceDTO.setQuantityAvailable(100);
        resourceDTO.setAmountBorrowed(0);
        resourceDTO.setLocalDate(LocalDate.parse("2021-11-13"));

        Mockito.when(repository.save(any())).thenReturn(resource);

        var result = service.create(resourceDTO);

        Assertions.assertNotNull(result, "el valor guardado no debe ser nulo");

        Assertions.assertEquals(resource.getName(), result.getName(), "el nombre no corresponde");
        Assertions.assertEquals(resource.getKind(), result.getKind(), "el tipo no corresponde");
        Assertions.assertEquals(resource.getThematic(), result.getThematic(), "La tematica no corresponde");
        Assertions.assertEquals(resource.getQuantityAvailable(), result.getQuantityAvailable(), "La cantidad no corresponde");
        Assertions.assertEquals(resource.getAmountBorrowed(), result.getAmountBorrowed(), "La cantidad no corresponde");
        Assertions.assertEquals(resource.getLocalDate(), result.getLocalDate(), "La fecha no corresponde");
    }

    @Test
    @DisplayName("Test get list resources success")
    void getListResources(){

        Mockito.when(repository.findAll()).thenReturn(resources());

        var result = service.list();

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("Don Quijote de la Mancha", result.get(0).getName());
        Assertions.assertEquals("Guerra y paz", result.get(1).getName());
        Assertions.assertEquals("Orgullo y prejuicio", result.get(2).getName());

    }

    @Test
    @DisplayName("Test to get resource by id")
    void getResourceById(){

        Mockito.when(repository.findById(Mockito.any())).thenReturn(resources().stream().findFirst());

        var result = service.getById(resources().get(0).getId());

        Assertions.assertEquals(resources().get(0).getId(), result.get().getId(), "el id debe corresponder");

        Assertions.assertEquals("Don Quijote de la Mancha", result.get().getName(), "el nombre debe corresponder");
        Assertions.assertEquals(30, result.get().getQuantityAvailable(), "la cantidad disponible debe ser igual");
        Assertions.assertEquals(LocalDate.parse("2021-11-13"), result.get().getLocalDate(), "la fecha de cuando se presto debe estar nula");
        Assertions.assertEquals(0, result.get().getAmountBorrowed(), "la cantidad prestada debe ser cero");
        Assertions.assertEquals("Novela", result.get().getKind(), "el tipo debe coincidir ");
        Assertions.assertEquals("Historia", result.get().getThematic(), "la tematica debe coincidir");

    }


    @Test
    @DisplayName("test para editar un recurso de manera exitosa")
    void update() {
        var resource = new ResourceDTO();
        resource.setId("1111");
        resource.setName("El ingenioso caballero Don Quijote de la Mancha");
        resource.setKind("Novela");
        resource.setThematic("Historia");
        resource.setQuantityAvailable(30);
        resource.setAmountBorrowed(0);
        resource.setLocalDate(LocalDate.parse("2021-11-13"));

        Mockito.when(repository.save(Mockito.any())).thenReturn(mapper.toResource(resource));
        Mockito.when(repository.findById(resource.getId())).thenReturn(resources().stream().findFirst());
        var result = service.update(resource);

        Assertions.assertNotNull(result, "el dato guardado no debe ser nullo");

        Assertions.assertEquals("1111", result.getId(), "el id debe corresponder");
        Assertions.assertEquals("El ingenioso caballero Don Quijote de la Mancha", result.getName(), "el nombre debe corresponder");
        Assertions.assertEquals(30, result.getQuantityAvailable(), "la cantidad disponible debe ser igual");
        Assertions.assertEquals(LocalDate.parse("2021-11-13"), result.getLocalDate(), "la fecha de cuando se presto debe estar nula");
        Assertions.assertEquals(0, result.getAmountBorrowed(), "la cantidad prestada debe ser cero");
        Assertions.assertEquals("Novela", result.getKind(), "el tipo debe coincidir ");
        Assertions.assertEquals("Historia", result.getThematic(), "la tematica debe coincidir");
    }


}