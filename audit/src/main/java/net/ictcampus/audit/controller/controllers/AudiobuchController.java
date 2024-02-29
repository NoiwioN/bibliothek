package net.ictcampus.audit.controller.controllers;

import net.ictcampus.audit.controller.services.AudiobuchService;
import net.ictcampus.audit.model.models.Audiobuch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
@RestController
@RequestMapping("/audiobücher")
public class AudiobuchController {
    private final AudiobuchService audiobuchService;
    @Autowired
    public AudiobuchController(AudiobuchService audiobuchService){
        this.audiobuchService = audiobuchService;
    }

    @GetMapping(path = "{id}")
    public Audiobuch findById(@PathVariable Integer id){
        try {
            return audiobuchService.findById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audiobuch not found");
        }
    }
    @GetMapping()
    public Iterable<Audiobuch> findAll(){
        try {
            return audiobuchService.findAll();
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audiobuch not found");
        }
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody Audiobuch audiobuch){
        try {
            audiobuchService.insert(audiobuch);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Audiobuch conflict");
        }
    }
    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Audiobuch audiobuch){
        try {
            audiobuchService.update(audiobuch);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Audiobuch conflict");
        }
    }
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        try {
            audiobuchService.deleteById(id);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audiobuch not found");
        }
    }
}
