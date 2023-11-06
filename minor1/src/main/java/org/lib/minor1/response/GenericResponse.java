package org.lib.minor1.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GenericResponse<T> {

    private String error;
    private String code;
    private T data;
    private HttpStatus status;


}
