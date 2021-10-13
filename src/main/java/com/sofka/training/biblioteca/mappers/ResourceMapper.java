package com.sofka.training.biblioteca.mappers;

import com.sofka.training.biblioteca.collections.Resource;
import com.sofka.training.biblioteca.dtos.ResourceDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResourceMapper {

    ResourceDTO toResourceDto(Resource resource);
    List<ResourceDTO> toResourcesDto(List<Resource> resources);

    @InheritInverseConfiguration
    Resource toResource(ResourceDTO resourceDTO);

}
