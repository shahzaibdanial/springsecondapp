package com.shzb.secondapp.controller;

import com.shzb.secondapp.entity.Person;
import com.shzb.secondapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @GetMapping
    public List<Person> getAllPerson(){
        return personService.getAllPersons();
    }
    @GetMapping("/{id}")
    public Person findPersonById(@PathVariable int id){
        return personService.findPersonById(id);
    }
    @PostMapping
    public ResponseEntity<?> addPerson(@Valid @RequestBody Person person, BindingResult result)
    {
        if(result.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            for(FieldError error:result.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Person>(personService.addPerson(person),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable int id,@Valid @RequestBody Person person,BindingResult result){
        if(result.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            for(FieldError error:result.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Person>(personService.updatePerson(person,id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id){
        personService.deletePerson(id);
        return new ResponseEntity<String>( "Record Deleted sucessfully with id"+id,HttpStatus.OK);
    }
}
