package net.ictcampus.audit.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.ictcampus.audit.controller.services.UserService;
import net.ictcampus.audit.model.models.Ausleihe;
import net.ictcampus.audit.model.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "/users")
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
            @ApiResponse(responseCode = "200", description = "User wurde gefunden", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Ausleihe.class))}),
            @ApiResponse(responseCode = "404", description = "User wurde nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
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
            @ApiResponse(responseCode = "200", description = "Benutzer wurden gefunden", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Benutzer wurde nicht gefunden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert")
    })
    public Iterable<User> findAll(@RequestParam(required = false) String username) {
        if(username !=null){
            try {
                User user = userService.getUserByUsername(username);
                ArrayList<User> u=new ArrayList<>();
                u.add(user);
                return u;
            } catch (EntityNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Benutzer wurde nicht gefunden");
            }
        }else{
            try {
                return userService.findAll();
            } catch (EntityNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Benutzer wurde nicht gefunden");
            }
        }

    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Aktualisiert einen Benutzer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Benutzer wurde aktualisiert"),
            @ApiResponse(responseCode = "404", description = "Benutzer konnte nicht gefunden werden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert"),
            @ApiResponse(responseCode = "409", description = "Benutzer konnte nicht aktualisiert werden"),
            @ApiResponse(responseCode="400", description = "Ungültiger Request")
    })
    public void update(@Valid @RequestBody User user) {
        try {
            userService.update(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Benutzer konnte nicht aktualisiert werden");
        }
    }
//    @GetMapping(path = "/username/{username}")
//    public User getUserByUsername(@PathVariable String username){
//
//    }

    @PostMapping(consumes = "application/json", path = "/sign-up")
    @Operation(summary = "Erstellt einen neuen Benutzer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Benutzer wurde erstellt"),
            @ApiResponse(responseCode = "409", description = "Benutzer konnte nicht erstellt werden"),
            @ApiResponse(responseCode = "403", description = "Nicht autorisiert"),
            @ApiResponse(responseCode="400", description = "Ungültiger Request")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Validated @RequestBody User user) {
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
