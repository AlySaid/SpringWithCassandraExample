package com.example.root.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.root.model.Interactions;

public interface InteractionRepository extends CrudRepository<Interactions, Serializable> {

	
	 
	//@Query("select * from interactions WHERE stratio_col = '{ \"query\" :{ \"type\" : \"match\", \"field\" : \"mediatype\", \"value\" :\"chat\" } }';")
	@Query("select * from interactions WHERE stratio_col = ?0 ")
	List<Interactions> getInteractions(String stratioValue);

}
