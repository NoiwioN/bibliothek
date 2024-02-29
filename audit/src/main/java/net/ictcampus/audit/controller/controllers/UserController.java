package net.ictcampus.audit.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.ictcampus.audit.controller.services.UserService;
import net.ictcampus.audit.model.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gibt einen bestimmten Benutzer zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Benutzer wurde nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert"),
            @ApiResponse(responseCode = "200", description = "Benutzer wurde gefunden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    })
    public User findById(@PathVariable Integer id) {
        try {
            return userService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Benutzer wurde nicht gefunden");
        }
    }

    @GetMapping
    @Operation(summary = "Gibt alle Benutzer zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Benutzer wurden gefunden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Benutzer konnten nicht gefunden werden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
    })
    public Iterable<User> findAll() {
        try {
            return userService.findAll();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Benutzer konnten nicht gefunden werden");
        }
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Aktualisiert einen Benutzer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Der Benutzer konnte aktualisiert werden"),
            @ApiResponse(responseCode = "404", description = "Benutzer nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert"),
            @ApiResponse(responseCode = "409", description = "Der Benutzer konnte nicht aktualisiert werden")
    })
    public void update(@Valid @RequestBody User user) {
        try {
            userService.update(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Der Benutzer konnte nicht aktualisiert werden");
        }
    }

    @PostMapping(consumes = "application/json", path = "/sign-up")
    @Operation(summary = "Erstellt einen neuen Benutzer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Benutzer wurde erstellt"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert"),
            @ApiResponse(responseCode = "409", description = "Benutzer konnte nicht erstellt werden")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Valid @RequestBody User user) {
        try {
            userService.signUp(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Benutzer konnte nicht erstellt werden");
        }
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Löscht einen Benutzer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Benutzer wurde gelöscht"),
            @ApiResponse(responseCode = "404", description = "Benutzer wurde nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
    })
    public void deleteById(@PathVariable Integer id) {
        try {
            userService.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Benutzer wurde nicht gefunden");
        }
    }
}
