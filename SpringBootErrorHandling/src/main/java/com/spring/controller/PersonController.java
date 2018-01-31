package com.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.data.Person;
import com.spring.data.PersonRepository;
import com.spring.errorhandling.EntityNotFoundException;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	private final static Logger LOGGER = Logger.getLogger(PersonController.class.getName());
	
	@Autowired
	PersonRepository personRepository;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void savePerson(@RequestBody Person person) {
		LOGGER.log(Level.INFO, "********************");
		personRepository.save(person);
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public List<Person> find() {
		LOGGER.log(Level.INFO, "********************");
		List<Person> result;
		result = new ArrayList<Person>();
		Iterable<Person> itemList = personRepository.findAll();
		for (Person item : itemList) {
			result.add(item);
		}
		return result;
	}
	
	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public Person findone(@PathVariable int id) throws EntityNotFoundException {
		
		Person result = personRepository.findOne(id);
		
		if(result == null) { // throw custom EntityNotFoundException when when no entity is found for a given parameter and value
			throw new EntityNotFoundException(Person.class,"id",String.valueOf(id));
		}
		
		return result;
		
	}

}
