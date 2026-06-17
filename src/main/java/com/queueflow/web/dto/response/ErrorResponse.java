package com.queueflow.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

@Schema(
        description = "Réponse standard d'erreur"
)
public record ErrorResponse(

        @Schema(
                example = "2026-06-17T21:15:30Z",
                description = "Date et heure de l'erreur"
        )
        Instant timestamp,

        @Schema(
                example = "409",
                description = "Code HTTP"
        )
        int status,

        @Schema(
                example = "CONFLICT",
                description = "Nom du statut HTTP"
        )
        String error,

        @Schema(
                example = "Un service avec le code 'CNI' existe déjà",
                description = "Message fonctionnel"
        )
        String message,

        @Schema(
                example = "/api/v1/service-desks",
                description = "URI appelée"
        )
        String path

) {
}