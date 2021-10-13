package com.sofka.training.biblioteca.services.interfaces;

import com.sofka.training.biblioteca.dtos.ResourceDTO;

import java.util.Optional;

public interface IResourceService {

    Iterable<ResourceDTO> list();
    ResourceDTO create(ResourceDTO resourceDTO);
    boolean delete(String id);
    Optional<ResourceDTO> getById(String id);
    ResourceDTO update(ResourceDTO resourceDTO);
    String checkResourceAvailability(String id);

}
