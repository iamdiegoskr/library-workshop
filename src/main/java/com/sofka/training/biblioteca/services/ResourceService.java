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

        Optional<Resource> resource = repository.findById(resourceDTO.getId());

        if(resource.isPresent()){

            resource.get().setId(resourceDTO.getId());
            resource.get().setName(resourceDTO.getName());
            resource.get().setKind(resourceDTO.getKind());
            resource.get().setThematic(resourceDTO.getThematic());
            resource.get().setQuantityAvailable(resourceDTO.getQuantityAvailable());
            resource.get().setAmountBorrowed(resourceDTO.getAmountBorrowed());
            resource.get().setLocalDate(resourceDTO.getLocalDate());

            return mapper.toResourceDto(repository.save(resource.get()));
        }
        throw new RuntimeException("El recurso no existe");
    }

    @Override
    public String checkResourceAvailability(String id) {

        return getById(id).map(resourceDTO -> {
            if(isAvailableResource(resourceDTO)){
                return "El recurso esta disponible";
            }
            return "El recurso no esta disponible," +
                    " la fecha de su ultimo prestamo fue el dia " + resourceDTO.getLocalDate();
        }).orElseThrow(()-> new RuntimeException("EL recurso que busca no existe"));

    }

    public boolean isAvailableResource(ResourceDTO resourceDTO){
        return resourceDTO.getQuantityAvailable()>resourceDTO.getAmountBorrowed();
    }

}
