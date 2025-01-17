package com.enviro.assessment.grad001.santosrafaelo.appresponse;

import com.enviro.assessment.grad001.santosrafaelo.dto.ErrorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {

    private String message;
    private String statusMessage;
    private String urlInstance;
    private String method;
    private HttpStatus statusCode;
    private List<ErrorDto> errors;
    private T result;
}
