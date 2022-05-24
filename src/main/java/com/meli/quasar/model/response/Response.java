package com.meli.quasar.model.response;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
public class Response{

    private String status;
    private String message;

    public Response(String status, String message) {
        super();
        this.message = message;
        this.status = status;
    }
}
