package com.maruli.sofco.object;

import lombok.Data;

;

@Data
public class BasicResponse {

    private Boolean isSucces;
    private String message;

    public BasicResponse(Boolean isSucces, String message) {
        this.isSucces = isSucces;
        this.message = message;
    }
}
