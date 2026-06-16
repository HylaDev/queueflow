package com.queueflow.web.dto.response;

import java.util.UUID;

public record ServiceDeskResponse(

        UUID id,

        String code,

        String name,

        String description,

        Boolean active
) {
}