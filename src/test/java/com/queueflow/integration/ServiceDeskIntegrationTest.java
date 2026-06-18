package com.queueflow.integration;

import com.queueflow.infrastructure.persistence.ServiceDeskJpaRepository;
import com.queueflow.infrastructure.persistence.entity.ServiceDeskEntity;
import com.queueflow.web.dto.request.CreateServiceDeskRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ServiceDeskIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ServiceDeskJpaRepository repository;

    @BeforeEach
    void setUp() {

        repository.deleteAll();
    }

    @Test
    @DisplayName("Should create service desk and persist it")
    void shouldCreateServiceDeskAndPersistIt()
            throws Exception {

        CreateServiceDeskRequest request =
                new CreateServiceDeskRequest(
                        "CNI",
                        "Carte d'identité",
                        "Gestion des cartes d'identité"
                );

        mockMvc.perform(
                        post("/api/v1/service-desks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("CNI"))
                .andExpect(jsonPath("$.name").value("Carte d'identité"))
                .andExpect(jsonPath("$.active").value(true));

        assertEquals(1, repository.count());
    }

    @Test
    @DisplayName("Should return all service desks")
    void shouldReturnAllServiceDesks()
            throws Exception {

        ServiceDeskEntity entity =
                new ServiceDeskEntity();

        entity.setCode("CNI");
        entity.setName("Carte d'identité");
        entity.setDescription("Gestion des cartes");
        entity.setActive(true);

        repository.save(entity);

        mockMvc.perform(
                        get("/api/v1/service-desks")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].code").value("CNI"))
                .andExpect(jsonPath("$[0].name").value("Carte d'identité"));
    }

    @Test
    @DisplayName("Should return bad request when request is invalid")
    void shouldReturnBadRequestWhenRequestIsInvalid()
            throws Exception {

        String request = """
                {
                    "code": "",
                    "name": ""
                }
                """;

        mockMvc.perform(
                        post("/api/v1/service-desks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should reject duplicate service desk code")
    void shouldRejectDuplicateServiceDeskCode()
            throws Exception {

        ServiceDeskEntity entity =
                new ServiceDeskEntity();

        entity.setCode("CNI");
        entity.setName("Carte d'identité");
        entity.setActive(true);

        repository.save(entity);

        CreateServiceDeskRequest request =
                new CreateServiceDeskRequest(
                        "CNI",
                        "Autre service",
                        "Description"
                );

        mockMvc.perform(
                        post("/api/v1/service-desks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isConflict());
    }
}