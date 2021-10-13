package com.sofka.training.biblioteca.services;

import com.sofka.training.biblioteca.collections.Resource;
import com.sofka.training.biblioteca.dtos.ResourceDTO;
import com.sofka.training.biblioteca.mappers.ResourceMapper;
import com.sofka.training.biblioteca.repositories.ResourceRepository;
import com.sofka.training.biblioteca.services.interfaces.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResourceService implements IResourceService {

    @Autowired
    private ResourceRepository repository;

    @Autowired
    private ResourceMapper mapper;


    @Override
    public Iterable<ResourceDTO> list() {
        return mapper.toResourcesDto(repository.findAll());
    }

    @Override
    public ResourceDTO create(ResourceDTO resourceDTO) {
        Resource resource = mapper.toResource(resourceDTO);
        return mapper.toResourceDto(repository.save(resource));
    }

    @Override
    public Optional<ResourceDTO> getById(String id) {
        return repository.findById(id)
                .map(resource -> mapper.toResourceDto(resource));
    }

    @Override
    public boolean delete(String id) {
        return getById(id).map(resourceDTO -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public ResourceDTO update(ResourceDTO resourceDTO) {
        return null;
    }
}
