package net.ictcampus.audit.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.ictcampus.audit.controller.services.GenreService;
import net.ictcampus.audit.model.models.Genre;
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

    @GetMapping
    @Operation(summary = "Zeigt alle Genres an")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genres were found successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))}),
            @ApiResponse(responseCode = "404", description = "Genres could not be found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))})})
    public Iterable<Genre> findAll(){
        try {
            return genreService.findAll();
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "genre not found");
        }
    }
    @GetMapping(path = "{id}")
    @Operation(summary = "Zeigt ein Genre an welches man mit seiner id suchen kann")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre was found successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))}),
            @ApiResponse(responseCode = "404", description = "Genre could not be found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))})})
    public Genre findById(@PathVariable Integer id){
        try {
            return genreService.findById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found");
        }
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Erstellt ein Genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genre was created successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))}),
            @ApiResponse(responseCode = "409", description = "Genre could not be created, already exists",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))})})
    public void insert(@RequestBody Genre genre){
        try {
            genreService.insert(genre);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Genre conflict");
        }
    }
    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Aktualisiert ein Genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre was updated successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))}),
            @ApiResponse(responseCode = "409", description = "Genre could not be updated, already exists",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Validation failed",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))})})
    public void update(@RequestBody Genre genre){
        try {
            genreService.update(genre);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Genre conflict");
        }
    }
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Löscht ein Genre nach seiner id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre was deleted successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))}),
            @ApiResponse(responseCode = "404", description = "Genre could not be found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))})})
    public void deleteById(@PathVariable Integer id){
        try {
            genreService.deleteById(id);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found");
        }
    }
}
