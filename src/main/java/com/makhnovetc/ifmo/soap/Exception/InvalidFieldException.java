package com.makhnovetc.ifmo.soap.Exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.makhnovetc.ifmo.soap.Exception.ExceptionBean")
public class InvalidFieldException extends Exception {
    private static String defMessage = "Invalid field.";

    private final ExceptionBean fault;

    public InvalidFieldException(String message, ExceptionBean fault){
        super(message);
        this.fault = fault;
    }

    public InvalidFieldException(String message, ExceptionBean fault, Throwable cause){
        super(message);
        this.fault = fault;
    }

    public InvalidFieldException(ExceptionBean fault){
        super(defMessage);
        this.fault = fault;
    }

    public ExceptionBean getFaultInfo() {
        return fault;
    }
}
