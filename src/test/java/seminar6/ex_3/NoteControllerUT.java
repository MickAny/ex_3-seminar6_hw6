package seminar6.ex_3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import seminar6.ex_3.controllers.NoteController;
import seminar6.ex_3.repositories.NoteRepository;

@ExtendWith(MockitoExtension.class)
public class NoteControllerUT {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteController noteController;

    private MockMvc mockMvc;

    @BeforeEach()
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    void testGetAllNotes() throws Exception {
        mockMvc.perform(get("/note"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }



}
