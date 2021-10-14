package com.sofka.training.biblioteca.repositories;

import com.sofka.training.biblioteca.collections.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResourceRepository extends MongoRepository<Resource, String> {

    List<Resource> findByKind(final String kind);
    Iterable<Resource> findByThematic(final String thematic);

}
