package net.ictcampus.audit.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.ictcampus.audit.controller.services.AusleiheService;
import net.ictcampus.audit.model.models.Ausleihe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/ausleihen")
public class AusleiheController {
    private final AusleiheService ausleiheService;

    @Autowired

    public AusleiheController(AusleiheService ausleiheService) {
        this.ausleiheService = ausleiheService;
    }
    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gibt eine bestimmte Ausleihe zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ausleihe wurde gefunden", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Ausleihe.class))}),
            @ApiResponse(responseCode = "404", description = "Ausleihe wurde nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
    })
    public Ausleihe findById(@PathVariable Integer id) {
        try {
            return ausleiheService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ausleihe wurde nicht gefunden");
        }
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gibt alle Ausleihen zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ausleihen wurden gefunden", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Ausleihe.class))}),
            @ApiResponse(responseCode = "404", description = "Ausleihen wurden nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
    })
    public Iterable<Ausleihe> findAll() {
        try {
            return ausleiheService.findAll();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ausleihen wurden nicht gefunden");
        }
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Erstellt eine neue Ausleihe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ausleihe wurde erstellt"),
            @ApiResponse(responseCode = "409", description = "Ausleihe konnte nicht erstellt werden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert"),
            @ApiResponse(responseCode="400", description = "Ungültiger Request")
    })
    public void insert(@Valid @RequestBody Ausleihe ausleihe) {
        try {
            ausleiheService.insert(ausleihe);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ausleihe konnte nicht erstellt werden");
        }
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Aktualisiert eine Ausleihe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ausleihe wurde aktualisiert"),
            @ApiResponse(responseCode = "404", description = "Ausleihe konnte nicht gefunden werden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert"),
            @ApiResponse(responseCode = "409", description = "Ausleihe konnte nicht aktualisiert werden"),
            @ApiResponse(responseCode="400", description = "Ungültiger Request")
    })
    public void update(@Valid @RequestBody Ausleihe ausleihe) {
        try {
            ausleiheService.update(ausleihe);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ausleihe konnte nicht aktualisiert werden");
        }
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Löschen einer Ausleihe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ausleihe wurde gelöscht"),
            @ApiResponse(responseCode = "404", description = "Ausleihe wurde nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
    })
    public void deleteById(@PathVariable Integer id) {
        try {
            ausleiheService.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ausleihe wurde nicht gefunden");
        }
    }

}
