package seminar6.ex_3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import seminar6.ex_3.repositories.NoteRepository;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class NoteControllerIT {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    NoteRepository noteRepository;

    @Test
    void testGetAllTasks()throws Exception{
        this.mockMvc.perform(get("/note"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(APPLICATION_JSON)
                );
    }

    @Test
    void testCreateTask() throws Exception{

        var requestBuilder = post("/note")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {                      
                            "title": "title1",
                            "text": "text1"
                        }
                        """);

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                                    {
                                        "title": "title1",
                                        "text": "text1"
                                    }
                                """),
                        jsonPath("$.id").exists()
                );

        assertEquals(1, this.noteRepository.findAll().size());
        assertEquals("title1", this.noteRepository.findAll().get(0).getTitle());
        assertEquals("text1", this.noteRepository.findAll().get(0).getText());
    }
}
