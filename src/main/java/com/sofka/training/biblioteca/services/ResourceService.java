package com.sofka.training.biblioteca.services;

import com.sofka.training.biblioteca.mappers.ResourceMapper;
import com.sofka.training.biblioteca.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository repository;

    @Autowired
    private ResourceMapper mapper;




}
