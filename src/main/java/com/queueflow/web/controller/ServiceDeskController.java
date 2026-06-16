package com.queueflow.web.controller;

import com.queueflow.application.service.ServiceDeskService;
import com.queueflow.domain.model.ServiceDesk;
import com.queueflow.web.dto.request.CreateServiceDeskRequest;
import com.queueflow.web.dto.response.ServiceDeskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/service-desks")
@Tag(
        name = "Service Desk",
        description = "Service Desk management API"
)
public class ServiceDeskController {

    private final ServiceDeskService serviceDeskService;

    public ServiceDeskController(
            ServiceDeskService serviceDeskService) {

        this.serviceDeskService = serviceDeskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a service desk",
            description = "Creates a new service desk"
    )
    public ServiceDeskResponse create(
            @Valid @RequestBody
            CreateServiceDeskRequest request) {

        ServiceDesk serviceDesk =
                toDomain(request);

        ServiceDesk created =
                serviceDeskService.create(serviceDesk);

        return toResponse(created);
    }

    private ServiceDesk toDomain(
            CreateServiceDeskRequest request) {

        ServiceDesk serviceDesk =
                new ServiceDesk();

        serviceDesk.setCode(request.code());
        serviceDesk.setName(request.name());
        serviceDesk.setDescription(
                request.description());

        return serviceDesk;
    }

    private ServiceDeskResponse toResponse(
            ServiceDesk serviceDesk) {

        return new ServiceDeskResponse(
                serviceDesk.getId(),
                serviceDesk.getCode(),
                serviceDesk.getName(),
                serviceDesk.getDescription(),
                serviceDesk.getActive()
        );
    }
}