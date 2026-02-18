package be.pxl.smarthome.api;

import be.pxl.smarthome.api.request.ScenarioRequest;
import be.pxl.smarthome.builders.ScenarioBuilder;
import be.pxl.smarthome.domain.Scenario;
import be.pxl.smarthome.exception.DuplicateScenarioException;
import be.pxl.smarthome.service.ScenarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ScenarioController.class)
public class ScenarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScenarioService scenarioService;

    @Captor
    private ArgumentCaptor<ScenarioRequest> scenarioRequestCaptor;

    private final Scenario scenario = ScenarioBuilder.aScenario()
            .withName("Leaving home")
            .build();

    @Test
    public void testScenarioIsCreatedSuccessfully() throws Exception {
        ScenarioRequest scenarioRequest = new ScenarioRequest("Leaving home.");
        mockMvc.perform(MockMvcRequestBuilders.post("")
                        .content(asJsonString(scenarioRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(scenarioService).createScenario(scenarioRequestCaptor.capture());
        ScenarioRequest createdScenario = scenarioRequestCaptor.getValue();
        assertEquals(createdScenario.name(), scenario.getName());
    }

    @Test
    public void throwDuplicateScenarioExceptionWhenScenarioAlreadyExists() throws Exception {
        ScenarioRequest scenarioRequest = new ScenarioRequest("Leaving home.");
        doThrow(DuplicateScenarioException.class)
                .when(scenarioService).createScenario(scenarioRequest);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("")
                        .content(asJsonString(scenarioRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
