package org.llamagas.servicelayer.model.response;

import lombok.Data;

@Data
public class GeneralResponse {
    private String code;
    private String message;
    private Object data;
}
