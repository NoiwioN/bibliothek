package net.ictcampus.audit.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.ictcampus.audit.controller.services.GenreService;
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
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;
    @Autowired
    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "Gibt ein bestimmtes Genre zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre wurde gefunden", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Ausleihe.class))}),
            @ApiResponse(responseCode = "404", description = "Genre wurde nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
    })
    public Genre findById(@PathVariable Integer id){
        try {
            return genreService.findById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre wurde nicht gefunden");
        }
    }
    @GetMapping
    @Operation(summary = "Gibt alle Genres zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre wurden gefunden", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Ausleihe.class))}),
            @ApiResponse(responseCode = "404", description = "Genre wurden nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
    })
    public Iterable<Genre> findAll(){
        try {
            return genreService.findAll();
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genres wurde nicht gefunden");
        }
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Erstellt ein Genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genre wurde erstellt"),
            @ApiResponse(responseCode = "409", description = "Genre konnte nicht erstellt werden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert"),
            @ApiResponse(responseCode="400", description = "Ungültiger Request")
    })
    public void insert(@Valid @RequestBody Genre genre){
        try {
            genreService.insert(genre);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Genre konnte nicht erstellt werden");
        }
    }
    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Aktualisiert ein Genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Genre wurde aktualisiert"),
            @ApiResponse(responseCode = "404", description = "Genre konnte nicht gefunden werden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert"),
            @ApiResponse(responseCode = "409", description = "Benutzer konnte nicht aktualisiert werden"),
            @ApiResponse(responseCode="400", description = "Ungültiger Request")
    })
    public void update(@Valid @RequestBody Genre genre){
        try {
            genreService.update(genre);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Genre konnte nicht aktualisiert werden");
        }
    }
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Löscht ein Genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Genre wurde gelöscht"),
            @ApiResponse(responseCode = "404", description = "Genre wurde nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
    })
    public void deleteById(@PathVariable Integer id){
        try {
            genreService.deleteById(id);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre wurde nicht gefunden");
        }
    }
}
