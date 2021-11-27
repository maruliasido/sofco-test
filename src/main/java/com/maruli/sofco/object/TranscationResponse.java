package com.maruli.sofco.object;

import lombok.Data;

@Data
public class TranscationResponse {
    private Boolean isSucces;
    private String transactionId;
    private String errorMessage;

    public TranscationResponse(Boolean isSucces, String transactionId, String errorMessage) {
        this.isSucces = isSucces;
        this.transactionId = transactionId;
        this.errorMessage = errorMessage;
    }
}
