package com.shzb.secondapp.service;

import com.shzb.secondapp.entity.Person;
import com.shzb.secondapp.exception.NotFoundException;
import com.shzb.secondapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
   public Person addPerson(Person person){
       personRepository.save(person);
       return person;
   }
   public List<Person> getAllPersons()
   {
     return  personRepository.findAll();
   }
   public Person findPersonById(int id)
   {
      Optional<Person> person = personRepository.findById(id);
      if (!person.isPresent()){
          throw  new NotFoundException("PERSON WITH id "+id+"does no exists");
   }
       return person.get();
   }

   public Person updatePerson(Person person,int id){
       Optional<Person> Opperson = personRepository.findById(id);
       if (!Opperson.isPresent()){
           throw  new NotFoundException("PERSON WITH id "+id+"does no exists");
       }
       person.setId(id);
       personRepository.save(person);
       return person;
   }

   public void deletePerson(int id){

       Optional<Person> person = personRepository.findById(id);
       if (!person.isPresent()){
           throw  new NotFoundException("PERSON WITH id "+id+"does no exists");
       }
       personRepository.delete(person.get());
   }



}
