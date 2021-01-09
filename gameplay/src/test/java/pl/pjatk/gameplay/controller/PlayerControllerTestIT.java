package pl.pjatk.gameplay.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.pjatk.gameplay.Player.controller.PlayerController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception{
        mockMvc.perform(get("/player")).andDo(print()).andExpect(status().isOk());
    }

}
