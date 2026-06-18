package com.queueflow.web.controller;

import com.queueflow.application.service.ServiceDeskService;
import com.queueflow.domain.model.ServiceDesk;
import com.queueflow.web.dto.request.CreateServiceDeskRequest;
import com.queueflow.web.dto.response.ErrorResponse;
import com.queueflow.web.dto.response.ServiceDeskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(
        name = "Service Desk",
        description = "Gestion d'un service"
)
public class ServiceDeskController {

    private final ServiceDeskService serviceDeskService;

    public ServiceDeskController(
            ServiceDeskService serviceDeskService) {

        this.serviceDeskService = serviceDeskService;
    }

    @PostMapping("/api/v1/service-desks")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Créer un service",
            description = "Permet de créer un nouveau service accessible aux usagers.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Service créé avec succès"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données invalides",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description =
                            "Un service avec ce code existe déjà",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description =
                            "Erreur interne du serveur",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ServiceDeskResponse create(@Valid @RequestBody CreateServiceDeskRequest request) {

        ServiceDesk serviceDesk = toDomain(request);

        ServiceDesk created = serviceDeskService.create(serviceDesk);

        return toResponse(created);
    }

    @GetMapping("/api/v1/service-desks")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Lister les services",
            description = " Retourne la liste des services. Possibilité de filtrer les services actifs ou inactifs")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Liste récupérer avec succès"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description =
                            "Erreur interne du serveur",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<ServiceDeskResponse> getServiceDesks(
            @io.swagger.v3.oas.annotations.Parameter(
                    description =
                            "Filtre sur l'état du service (true = actif, false = inactif)",
                    example = "true"
            )
            @RequestParam(required = false)
            Boolean active){

        return serviceDeskService
                .getServiceDesks(active)
                .stream()
                .map(this::toResponse)
                .toList();
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