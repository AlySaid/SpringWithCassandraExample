package com.example.root.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.repository.CrudRepository;

import com.example.root.model.SimpleTable;

@EnableCassandraRepositories
public interface SimpleTableRepository extends CrudRepository<SimpleTable, Serializable> {

	Optional<SimpleTable>findByIdAndName(String id, String name);

	@Query("Select * from Simple_Table where name = ?0 ;")
	Optional<List<SimpleTable>> findNameWithQuery(String name);
	
}
