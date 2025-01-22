package org.llamagas.servicelayer.constants;

import lombok.Getter;

@Getter
public enum ResponsesCodes {
    SUCCESSFUL("L001","Ejecución exitosa"),
    OBJECT_NOT_FOUND("L002","No encontró la entidad"),
    PARAMETER_FAILED("L003","Error en los parametros"),
    FAILED("L900","Ocurrió un error durante la ejecución"),
    UNAUTHORIZED("L401","Credencales incorrectas");

    private final String code;
    private final String description;

    ResponsesCodes(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
