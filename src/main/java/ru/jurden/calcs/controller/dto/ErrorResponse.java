package ru.jurden.calcs.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import ru.jurden.calcs.enums.ErrorCode;

import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class ErrorResponse {
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("message")
    private String message;

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(ErrorCode.VALIDATION_ERROR.getCode(),
                bindingResult.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining("; ")));
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
    }

}
