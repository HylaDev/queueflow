package com.queueflow.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;


public record ServiceDeskResponse(

        UUID id,

        String code,

        String name,

        String description,

        Boolean active
) {
}