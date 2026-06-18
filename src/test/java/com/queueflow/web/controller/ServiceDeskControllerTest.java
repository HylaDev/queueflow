package com.queueflow.web.controller;

import com.queueflow.application.service.ServiceDeskService;
import com.queueflow.domain.model.ServiceDesk;
import com.queueflow.web.dto.request.CreateServiceDeskRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ServiceDeskController.class)
class ServiceDeskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ServiceDeskService serviceDeskService;

    @Test
    @DisplayName("Should create service desk and return 201")
    void shouldCreateServiceDeskAndReturnCreated() throws Exception {

        ServiceDesk serviceDesk = new ServiceDesk();

        serviceDesk.setId(UUID.randomUUID());
        serviceDesk.setCode("CNI");
        serviceDesk.setName("Carte d'identité");
        serviceDesk.setDescription("Gestion CNI");
        serviceDesk.setActive(true);
        serviceDesk.setCreatedAt(Instant.now());
        serviceDesk.setUpdatedAt(Instant.now());

        when(serviceDeskService.create(any(ServiceDesk.class)))
                .thenReturn(serviceDesk);

        CreateServiceDeskRequest request =
                new CreateServiceDeskRequest(
                        "CNI",
                        "Carte d'identité",
                        "Gestion CNI"
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
    }

    @Test
    @DisplayName("Should return bad request when request is invalid")
    void shouldReturnBadRequestWhenRequestIsInvalid() throws Exception {

        String invalidBody = """
                {
                    "code": "",
                    "name": ""
                }
                """;

        mockMvc.perform(
                        post("/api/v1/service-desks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invalidBody)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return all service desks")
    void shouldReturnAllServiceDesks() throws Exception {

        ServiceDesk service1 = new ServiceDesk();
        service1.setId(UUID.randomUUID());
        service1.setCode("CNI");
        service1.setName("Carte d'identité");
        service1.setActive(true);

        ServiceDesk service2 = new ServiceDesk();
        service2.setId(UUID.randomUUID());
        service2.setCode("PASS");
        service2.setName("Passeport");
        service2.setActive(true);

        when(serviceDeskService.getServiceDesks(null))
                .thenReturn(List.of(service1, service2));

        mockMvc.perform(
                        get("/api/v1/service-desks")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("Should return only active service desks")
    void shouldReturnOnlyActiveServiceDesks() throws Exception {

        ServiceDesk service = new ServiceDesk();

        service.setId(UUID.randomUUID());
        service.setCode("CNI");
        service.setName("Carte d'identité");
        service.setActive(true);

        when(serviceDeskService.getServiceDesks(true))
                .thenReturn(List.of(service));

        mockMvc.perform(
                        get("/api/v1/service-desks")
                                .param("active", "true")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].active").value(true));
    }
}