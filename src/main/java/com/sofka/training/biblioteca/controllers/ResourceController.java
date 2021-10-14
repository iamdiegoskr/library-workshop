package com.sofka.training.biblioteca.controllers;

import com.sofka.training.biblioteca.dtos.ResourceDTO;
import com.sofka.training.biblioteca.services.interfaces.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/resources")
public class ResourceController {

    @Autowired
    private IResourceService service;

    @GetMapping()
    public Iterable<ResourceDTO> listResources(){
        return service.list();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResourceDTO> getResourceById(@PathVariable() String id){
        return service.getById(id)
                .map(resourceDTO -> new ResponseEntity<>(resourceDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping(value = "/create")
    public ResponseEntity<ResourceDTO> createResource(@RequestBody ResourceDTO resourceDTO){
        return new ResponseEntity<>(service.create(resourceDTO),HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteResource(@PathVariable String id){

        if(service.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResourceDTO> updateResource(@RequestBody ResourceDTO resourceDTO){
        return new ResponseEntity<>(service.update(resourceDTO),HttpStatus.OK);
    }

    @GetMapping(value = "/available/{id}")
    public ResponseEntity<String> validateResourceAvailability(@PathVariable() String id){
        return new ResponseEntity<>(service.checkResourceAvailability(id),HttpStatus.OK);
    }

    @PutMapping(value = "/lend/{id}")
    public ResponseEntity<String> lendResource(@PathVariable() String id){
        return new ResponseEntity<>(service.lendRecourse(id),HttpStatus.OK);
    }

    @PutMapping(value = "/return/{id}")
    public ResponseEntity<String> returnResource(@PathVariable() String id){
        return new ResponseEntity<>(service.returnResource(id),HttpStatus.OK);
    }

    @GetMapping("/recommend/type/{kind}")
    public ResponseEntity<List<ResourceDTO>> recommendedByType(@PathVariable() String kind){
        return new ResponseEntity<>(service.recommendByResourceType(kind),HttpStatus.OK);
    }

    @GetMapping("/recommend/thematic/{thematic}")
    public ResponseEntity<List<ResourceDTO>> recommendedByThematic(@PathVariable() String thematic){
        return new ResponseEntity<>(service.recommendByTheme(thematic),HttpStatus.OK);
    }

}
