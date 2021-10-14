package com.sofka.training.biblioteca.services.interfaces;

import com.sofka.training.biblioteca.dtos.ResourceDTO;

import java.util.List;
import java.util.Optional;

public interface IResourceService {

    List<ResourceDTO> list();
    ResourceDTO create(ResourceDTO resourceDTO);
    boolean delete(String id);
    Optional<ResourceDTO> getById(String id);
    ResourceDTO update(ResourceDTO resourceDTO);
    String checkResourceAvailability(String id);
    String lendRecourse(String id);
    String returnResource(String id);
    List<ResourceDTO> recommendByResourceType(String kind);
    List<ResourceDTO> recommendByTheme(String thematic);
    List<ResourceDTO> recommendByThemeAndType(String kind, String thematic);

}
