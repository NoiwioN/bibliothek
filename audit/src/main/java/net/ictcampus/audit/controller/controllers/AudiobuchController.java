package net.ictcampus.audit.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.ictcampus.audit.controller.services.AudiobuchService;
import net.ictcampus.audit.model.models.Audiobuch;
import net.ictcampus.audit.model.models.Genre;
import net.ictcampus.audit.model.models.User;
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
    @Operation(summary = "Returns a specific Audiobuch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))}),

            @ApiResponse(responseCode = "404", description = "Audiobuch not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public Audiobuch findById(@PathVariable Integer id){
        try {
            return audiobuchService.findById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audiobuch not found");
        }
    }
    @GetMapping()
    @Operation(summary = "Returns all Audiobücher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Audiobuch not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public Iterable<Audiobuch> findAll(){
        try {
            return audiobuchService.findAll();
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audiobuch not found");
        }
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a Genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genre created successfully"),
            @ApiResponse(responseCode = "409", description = "Genre could not be created"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public void insert(@RequestBody Audiobuch audiobuch){
        try {
            audiobuchService.insert(audiobuch);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Audiobuch conflict");
        }
    }
    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Aktualisiert ein Audiobuch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Audiobuch was updated successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))}),
            @ApiResponse(responseCode = "409", description = "Audiobuch could not be updated, already exists",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Validation failed",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))})})
    public void update(@RequestBody Audiobuch audiobuch){
        try {
            audiobuchService.update(audiobuch);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Audiobuch conflict");
        }
    }
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Löscht ein Audiobuch nach id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Audiobuch was deleted successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Genre.class))}),
            @ApiResponse(responseCode = "404", description = "Audiobuch could not be found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Genre.class))})})
    public void deleteById(@PathVariable Integer id){
        try {
            audiobuchService.deleteById(id);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audiobuch not found");
        }
    }
}
