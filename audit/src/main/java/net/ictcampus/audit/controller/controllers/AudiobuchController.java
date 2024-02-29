package net.ictcampus.audit.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.ictcampus.audit.controller.services.AudiobuchService;
import net.ictcampus.audit.model.models.Audiobuch;
import net.ictcampus.audit.model.models.Ausleihe;
import net.ictcampus.audit.model.models.Genre;
import net.ictcampus.audit.model.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/audiobuecher")
public class AudiobuchController {
    private final AudiobuchService audiobuchService;
    @Autowired
    public AudiobuchController(AudiobuchService audiobuchService){
        this.audiobuchService = audiobuchService;
    }
    @GetMapping(path = "{id}")
    @Operation(summary = "Gibt ein bestimmtes Audiobuch zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Audiobuch wurde gefunden", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Ausleihe.class))}),
            @ApiResponse(responseCode = "404", description = "Audiobuch wurde nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
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
            @ApiResponse(responseCode = "200", description = "Audiobuch wurde gefunden", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Audiobuch wurde nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
    })
    public Iterable<Audiobuch> findAll(){
        try {
            return audiobuchService.findAll();
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audiobuch wurde nicht gefunden");
        }
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Erstellt ein Audiobuch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Audibuch wurde erstellt"),
            @ApiResponse(responseCode = "409", description = "Audibuch konnte nicht erstellt werden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert"),
            @ApiResponse(responseCode="400", description = "Ungültiger Request")
    })
    public void insert(@Valid @RequestBody Audiobuch audiobuch){
        try {
            audiobuchService.insert(audiobuch);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Audibuch konnte nicht erstellt werden");
        }
    }
    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Aktualisiert ein Audiobuch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Audiobuch wurde aktualisiert"),
            @ApiResponse(responseCode = "409", description = "Audiobuch konnte nicht aktualisiert werden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert"),
            @ApiResponse(responseCode = "409", description = "Benutzer konnte nicht aktualisiert werden"),
            @ApiResponse(responseCode="400", description = "Ungültiger Request")
    })
    public void update(@Valid @RequestBody Audiobuch audiobuch){
        try {
            audiobuchService.update(audiobuch);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Audiobuch konnte nicht aktualisiert werden");
        }
    }
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Löschet ein Audiobuch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Audiobuch wurde gelöscht"),
            @ApiResponse(responseCode = "404", description = "Audiobuch wurde nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
    })
    public void deleteById(@PathVariable Integer id){
        try {
            audiobuchService.deleteById(id);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audiobuch wurde nicht gefunden");
        }
    }
}
