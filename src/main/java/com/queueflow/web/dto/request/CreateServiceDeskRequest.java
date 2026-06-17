package com.queueflow.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateServiceDeskRequest(

        @Schema(
                example = "CNI",
                description = "Code unique du service"
        )
        @NotBlank(message = "Le code est obligatoire")
        String code,

        @Schema(
                example = "Carte d'identité",
                description = "Nom du service"
        )
        @NotBlank(message = "Le nom est obligatoire")
        String name,

        @Schema(
                example = "Service de gestion des cartes d'identité"
        )
        String description
) {
}