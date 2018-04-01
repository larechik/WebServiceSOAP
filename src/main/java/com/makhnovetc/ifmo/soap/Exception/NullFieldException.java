package com.makhnovetc.ifmo.soap.Exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.makhnovetc.ifmo.soap.Exception.ExceptionBean")
public class NullFieldException extends Exception{
    private static String defMessage = "Invalid field. Field is null or empty.";
    private final ExceptionBean fault;

    public NullFieldException(String message, ExceptionBean fault){
        super(message);
        this.fault = fault;
    }

    public NullFieldException(String message, ExceptionBean fault, Throwable cause){
        super(message);
        this.fault = fault;
    }

    public NullFieldException(ExceptionBean fault){
        super(defMessage);
        this.fault = fault;
    }

    public ExceptionBean getFaultInfo() {
        return fault;
    }
}
