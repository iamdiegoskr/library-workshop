package com.sofka.training.biblioteca.services.interfaces;

import com.sofka.training.biblioteca.dtos.ResourceDTO;

public interface IResourceService {

    Iterable<ResourceDTO> list();
    ResourceDTO create(ResourceDTO resourceDTO);
    void delete(Integer id);
    ResourceDTO getById(Integer id);
    ResourceDTO update(ResourceDTO resourceDTO);

}
