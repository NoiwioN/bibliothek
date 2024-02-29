package net.ictcampus.audit.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.ictcampus.audit.controller.services.GenreService;
import net.ictcampus.audit.model.models.Genre;
import net.ictcampus.audit.model.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

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
            @ApiResponse(responseCode = "200", description = "Ergolgreich",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))}),

            @ApiResponse(responseCode = "404", description = "Genre nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Verboten")
    })
    public Genre findById(@PathVariable Integer id){
        try {
            return genreService.findById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre nicht gefunden");
        }
    }
    @GetMapping
    @Operation(summary = "Gibt ein alle Genres zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Erfolgreich",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Genres nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Verboten")
    })
    public Iterable<Genre> findAll(){
        try {
            return genreService.findAll();
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre nicht gefunden");
        }
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Erstellt ein Genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genre wurde erfolgreich erstellt"),
            @ApiResponse(responseCode = "409", description = "Genre konnte nicht erstellt werden"),
            @ApiResponse(responseCode = "403", description = "Verboten")
    })
    public void insert(@RequestBody Genre genre){
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
            @ApiResponse(responseCode = "409", description = "Genre konnte nicht aktualisiert werden"),
            @ApiResponse(responseCode = "403", description = "Verboten")
    })
    public void update(@RequestBody Genre genre){
        try {
            genreService.update(genre);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Genre konnte nicht aktualisiert werden");
        }
    }
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes a Genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Genre wurde gelöscht"),
            @ApiResponse(responseCode = "404", description = "Genre nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Verboten")
    })
    public void deleteById(@PathVariable Integer id){
        try {
            genreService.deleteById(id);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre nicht gefunden");
        }
    }
}
