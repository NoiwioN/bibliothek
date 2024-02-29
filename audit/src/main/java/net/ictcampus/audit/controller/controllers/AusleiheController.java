package net.ictcampus.audit.controller.controllers;

import net.ictcampus.audit.controller.services.AusleiheService;
import net.ictcampus.audit.model.models.Ausleihe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path="/ausleihen")
public class AusleiheController {
    private final AusleiheService ausleiheService;
    @Autowired

    public AusleiheController(AusleiheService ausleiheService) {
        this.ausleiheService = ausleiheService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ausleihe> findAll(){
        try{
            return ausleiheService.findAll();
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Es konnten keine Ausleihen gefunden werden");
        }
    }

    @GetMapping(path = "{id}")
    public Ausleihe findById(@PathVariable Integer id){
        try{
            return ausleiheService.findById(id);
        }catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Diese Ausleihe wurde nicht gefunden");
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody Ausleihe ausleihe){
        try {
            ausleiheService.insert(ausleihe);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ausleihe konnte nicht erfasst werden");
        }
    }
    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Ausleihe ausleihe){
        try {
            ausleiheService.update(ausleihe);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ausleihe konnte nicht aktualisiert werden");
        }
    }
    @DeleteMapping(path="{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        try {
            ausleiheService.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ausleihe wurde nicht gefunden");
        }
    }

}
