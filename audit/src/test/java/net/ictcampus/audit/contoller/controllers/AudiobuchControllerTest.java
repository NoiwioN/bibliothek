package net.ictcampus.audit.contoller.controllers;

import net.ictcampus.audit.controller.controllers.AudiobuchController;
import net.ictcampus.audit.controller.services.AudiobuchService;
import net.ictcampus.audit.controller.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
public class AudiobuchControllerTest {
    private static final String JSON_ALLE_AUDIOBUECHER = "[" +
            "{\"id\":1, \"titel\": \"Audiobuch1\", \"laenge\": 50000, \"genre\": { \"id\": 1, \"name\": \"Fantasy\"}}, " +
            "{\"id\":2, \"titel\": \"Audiobuch2\", \"laenge\": 50000, \"genre\": { \"id\": 1, \"name\": \"Fantasy\"}}, " +
            "{\"id\":3, \"titel\": \"Audiobuch3\", \"laenge\": 50000, \"genre\": { \"id\": 1, \"name\": \"Fantasy\"}}]";

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
     * GET-Request: Alle Audiobuecher werden herausgegeben und getestet, ob sie im richtigen JSON-Format
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
    @Test
    public void checkGet_whenValidName_thenAudiobuecherIsReturned() throws Exception {
        // gibt alle Filme aus, sobald findAll im gemockten MovieService aufgerufen wird
        doReturn(getTestAudiobuecher()).when(audiobuchService).findAll();

        // GET-Request über localhost:8080/movies "geschickt"
        mockMvc.perform(get("/audiobuecher"))
                // 200 (OK) wird erwartet -> bei erfolgreicher Abfrage, bekommen wir in der Regel
                // den Statuscode 200 zurück
                .andExpect(status().isOk())
                // wir erwarten, dass der Inhalt der Abfrage mit unserem JSON-Gerüst (unsere oben
                // definierte Konstante) übereinstimmt
                .andExpect(content().json(JSON_ALLE_AUDIOBUECHER));
    }
    /**
     *  GET-Request: Audiobuch mit der ID 1 wird herausgegeben und getestet, ob sie im richtigen JSON-Format
     * geschickt werden
     * @throws Exception
     */
    @Test
    public void checkGet_whenIdGiven_thenIsOk() throws Exception {
        // GET-Request über localhost:8080/movies/1 wird "ausgeführt"
        mockMvc.perform(get("/audiobuecher/1"))
                // Status 200 (OK) wird erwartet
                .andExpect(status().isOk());

        // über Mockito wird verifiziert, ob die ID bei findById der ID 1 entspricht
        Mockito.verify(audiobuchService).findById(eq(1));
    }

    /**
     *  DELETE-Request: Audiobücher mit der ID 1 wird gelöscht und überprüft
     * @throws Exception
     */
    @Test
    public void checkDelete_whenIdGiven_thenIsOk() throws Exception {
        // DELETE-Request über localhost:8080/movies/1 wird "ausgeführt"
        mockMvc.perform(delete("/audiobuecher/1"))
                // Status 204 (NO CONTENT) wird erwartet
                .andExpect(status().isNoContent());

        // über Mockito wird verifiziert, ob die ID bei deleteById der ID 1 entspricht
        Mockito.verify(audiobuchService).deleteById(eq(1));
    }

}