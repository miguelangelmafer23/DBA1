package com.bosonit.EJ2.exceptions;

import java.util.Date;

public class CustomError {
    private Date timestamp;

    private Integer HttpCode;
    private String mensaje;



    public CustomError(Date timestamp, Integer HttpCode,String message) {
        super();
        this.timestamp = timestamp;
        this.HttpCode=HttpCode;
        this.mensaje = message;
    }

    public Integer getHttpCode() {
        return HttpCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMensaje() {
        return mensaje;
    }


}

