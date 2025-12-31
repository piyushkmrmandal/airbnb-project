package com.learning.project.airbnbapp.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data @Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiError {
    HttpStatus status;
    String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<String> errors;
}
