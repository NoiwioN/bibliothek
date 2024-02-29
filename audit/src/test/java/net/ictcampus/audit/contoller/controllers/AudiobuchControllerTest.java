package net.ictcampus.audit.contoller.controllers;

import net.ictcampus.audit.controller.services.AudiobuchService;
import net.ictcampus.audit.controller.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.TimeZone;

public class AudiobuchControllerTest {
    private static final String JSON_ALL_MOVIES = "[{\"id\":1, \"name\": \"Movie1\", \"duration\": 120, \"genre\": { \"id\": 1, \"name\": \"Melodrama\"}}, " +
            "{\"id\":2, \"name\": \"Movie2\", \"duration\": 120, \"genre\": { \"id\": 1, \"name\": \"Melodrama\"}}, "+
            "{\"id\":3, \"name\": \"Movie3\", \"duration\": 120, \"genre\": { \"id\": 1, \"name\": \"Melodrama\"}}]";

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
}
