package com.makhnovetc.ifmo.soap.Exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.makhnovetc.ifmo.soap.Exception.InvalidDateFormatExceptionBean")
public class InvalidDateFormatException extends Exception{
    private final InvalidDateFormatExceptionBean fault;

    public InvalidDateFormatException(String message, InvalidDateFormatExceptionBean fault){
        super(message);
        this.fault = fault;
    }

    public InvalidDateFormatException(String message, InvalidDateFormatExceptionBean fault, Throwable cause){
        super(message);
        this.fault = fault;
    }

    public InvalidDateFormatExceptionBean getFaultInfo() {
        return fault;
    }
}
