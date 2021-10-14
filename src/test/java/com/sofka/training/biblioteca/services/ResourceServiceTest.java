package com.sofka.training.biblioteca.services;

import com.sofka.training.biblioteca.collections.Resource;
import com.sofka.training.biblioteca.dtos.ResourceDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ResourceServiceTest {

    @MockBean
    private ResourceRepository repository;

    @Autowired
    private ResourceService service;

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

        var resource = new Resource();
        resource.setId("1111");
        resource.setName("El amor en los tiempos del cólera");
        resource.setKind("Novela");
        resource.setThematic("Romance");
        resource.setQuantityAvailable(10);
        resource.setAmountBorrowed(0);
        resource.setLocalDate(LocalDate.parse("2021-11-13"));

        var resource2 = new Resource();
        resource2.setId("2222");
        resource2.setName("Cumbres borrascosas");
        resource2.setKind("Novela");
        resource2.setThematic("Romance");
        resource2.setQuantityAvailable(30);
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


        var list = new ArrayList<Resource>();

        list.add(resource);
        list.add(resource2);
        list.add(resource3);

        Mockito.when(repository.findAll()).thenReturn(list);

        var result = service.list();

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(resource.getName(), result.get(0).getName());
        Assertions.assertEquals(resource2.getName(), result.get(1).getName());
        Assertions.assertEquals(resource3.getName(), result.get(2).getName());

    }
}