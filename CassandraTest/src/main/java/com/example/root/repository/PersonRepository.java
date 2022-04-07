package com.example.root.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.example.root.model.Person;

public interface PersonRepository extends CrudRepository<Person, Serializable> {

}
