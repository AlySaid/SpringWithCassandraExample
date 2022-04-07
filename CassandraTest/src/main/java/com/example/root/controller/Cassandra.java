package com.example.root.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.root.model.Interactions;
import com.example.root.model.SimpleTable;
import com.example.root.repository.InteractionRepository;
import com.example.root.repository.SimpleTableRepository;

@RestController
@RequestMapping("/cassandra")
public class Cassandra {
	
	Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	SimpleTableRepository cassandraRepository;
	
	@Autowired
	private CassandraOperations cassandraOperations;
	
	@Autowired 
	InteractionRepository interactionRepository;
	
	
	@PostMapping
	public ResponseEntity<SimpleTable>saveToSimpleTable(@RequestBody SimpleTable simpleTable){
		
		return new ResponseEntity<SimpleTable>(cassandraRepository.save(simpleTable), HttpStatus.OK);
 
	}
	
	//@GetMapping(path = "/{id}/{name}")
	@GetMapping(path = "/{name}")
	//private ResponseEntity<SimpleTable> getByIdAndName(@PathVariable String id, @PathVariable String name) {
		
	private ResponseEntity<List<SimpleTable>> getByIdAndName( @PathVariable String name) {
		//Optional<SimpleTable> fetchDate = cassandraRepository.findByIdAndName(id, name);
		Optional<List<SimpleTable>> fetchDate = cassandraRepository.findNameWithQuery(name);
		
		/**
		 
		  	@Query("SELECT * FROM istqm.interactions WHERE stratio_col = '{\"query\" : {\"type\" : \"match\",\"field\" : \"id\",\"value\" :\""+"?0"+"\"}}'") 
			@Query("SELECT count(*) FROM istqm.interactions WHERE stratio_col = '{\"query\" : {\"type\" : \"match\",\"field\" : \"mediatype\",\"value\" :"+type+" }}'") 

		 */
		String type = "chat";
		String stratio_col = "'{\"query\" : {\"type\" : \"match\",\"field\" : \"id\",\"value\" :\""+type+"\"}}'"; 
		System.out.println(stratio_col);
		
		
		if (!fetchDate.isPresent()) {
			
		}
		return new ResponseEntity<List<SimpleTable>>(fetchDate.get(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/getInteractions/{mediaType}")
	private ResponseEntity<List<Interactions>> getInteractions(@PathVariable String mediaType){
		
		String stratio_col = "{ \"query\" :{ \"type\" : \"match\", \"field\" : \"mediatype\", \"value\" :\""+mediaType+"\" } }';";
		
		List<Interactions> fetchDate = interactionRepository.getInteractions(stratio_col);
//		if (!fetchDate.isPresent()) {
//			
//		}
		
		return  new ResponseEntity<List<Interactions>>(fetchDate, HttpStatus.OK);
	}
}
