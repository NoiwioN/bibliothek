package net.ictcampus.audit.controller.controllers;

import net.ictcampus.audit.controller.services.AudiobuchService;
import net.ictcampus.audit.controller.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.TimeZone;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import java.util.TimeZone;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AudiobuchController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AudiobuchControllerTestM {
    private static final String JSON_ALL_MOVIES = "[" +
            "{\"id\":1, \"name\": \"Audiobuch1\", \"duration\": \"50000\", \"genre\": { \"id\": 1, \"name\": \"Fantasy\"}}, " +
            "{\"id\":2, \"name\": \"Audiobuch2\", \"duration\": \"50000\", \"genre\": { \"id\": 1, \"name\": \"Fantasy\"}}, " +
            "{\"id\":3, \"name\": \"Audiobuch3\", \"duration\": \"50000\", \"genre\": { \"id\": 1, \"name\": \"Fantasy\"}}]";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AudiobuchService audiobuchService;

    // muss mitgegeben werden, da wir für Requests angemeldet sein müssen
    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    // dieser kommt zusätzlich mit, da das Passwort verschlüsselt und entschlüsselt wird
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // vor jedem Testfall wird die Zeitzone gemäss der DB-Einstellungen gesetzt, ansonsten könnte es
    // zu Fehler bei der Abfrage kommen
    @BeforeEach
    public void prepare() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    /**
     * GET-Request: Alle Filme werden herausgegeben und getestet, ob sie im richtigen JSON-Format
     * geschickt werden
     * @throws Exception
     */
    @Test
    public void checkPost_whenNewAudiobook_thenIsOk() throws Exception{
        mockMvc.perform(post("/audiobuecher")
                .contentType("application/json")
                .content("{\"id\": 99, \"titel\": \"Harry Potter and the Philosopher's Stone\", \"laenge\": 500, \"autor\": \"J.K. Rowling\", \"genre\": { \"id\": 1, \"name\": \"Fantasy\" }, \"erscheinungsjahr\": 1997}"
                )
        ).andExpect(status().isCreated());
    }
}