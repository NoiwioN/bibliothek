package net.ictcampus.audit.contoller.controllers;

import net.ictcampus.audit.controller.controllers.AudiobuchController;
import net.ictcampus.audit.controller.services.AudiobuchService;
import net.ictcampus.audit.controller.services.UserDetailsServiceImpl;
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

import static net.ictcampus.audit.utils.TestDataUtil.getTestAudiobuecher;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = AudiobuchController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AudiobuchControllerTestjulia {
    private static final String JSON_ALLE_AUDIOBUECHER = "[" +
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
     * GET-Request: Alle Audiobücher werden herausgegeben und getestet, ob sie im richtigen JSON-Format
     * geschickt werden
     * @throws Exception
     */
    @Test
    public void schaueGet_WennKeineParam_dannALLEAUDIOBUECHERZurueckgeben() throws Exception {
        // gibt alle Audiobücher aus, sobald findAll im gemockten MovieService aufgerufen wird
        doReturn(getTestAudiobuecher()).when(audiobuchService).findAll();

        // GET-Request über localhost:8080/movies "geschickt"
        mockMvc.perform(get("/audiobücher"))
                // 200 (OK) wird erwartet -> bei erfolgreicher Abfrage, bekommen wir in der Regel
                // den Statuscode 200 zurück
                .andExpect(status().isOk())
                // wir erwarten, dass der Inhalt der Abfrage mit unserem JSON-Gerüst (unsere oben
                // definierte Konstante) übereinstimmt
                .andExpect(content().json(JSON_ALLE_AUDIOBUECHER));
    }
}
