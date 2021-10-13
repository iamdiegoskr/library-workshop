package com.sofka.training.biblioteca.repositories;

import com.sofka.training.biblioteca.collections.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResourceRepository extends MongoRepository<Resource, String> {
}
