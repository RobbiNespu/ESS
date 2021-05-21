package io.robbinespu.ess.business.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
@Setter
public class UserRestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;

    public UserRestException(String message) {
        this.message = message;
    }

}