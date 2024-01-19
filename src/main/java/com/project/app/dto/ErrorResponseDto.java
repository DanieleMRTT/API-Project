package com.project.app.dto;


import com.project.app.enumeration.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "The error model related to the application")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    @Schema(description = "A predefined, documented error code, related to some predefined, documented scenario")
    private ErrorCode errorCode;
    @Schema(description = "A brief description of the error")
    private String errorMessage;
}
