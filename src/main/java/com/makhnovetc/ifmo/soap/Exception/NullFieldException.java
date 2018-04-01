package com.makhnovetc.ifmo.soap.Exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.makhnovetc.ifmo.soap.Exception.NullFieldException")
public class NullFieldException extends Exception{

    private final NullFieldExceptionBean fault;

    public NullFieldException(String message, NullFieldExceptionBean fault){
        super(message);
        this.fault = fault;
    }

    public NullFieldException(String message, NullFieldExceptionBean fault, Throwable cause){
        super(message);
        this.fault = fault;
    }

    public NullFieldExceptionBean getFaultInfo() {
        return fault;
    }
}
