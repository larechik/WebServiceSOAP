package com.makhnovetc.ifmo.soap.lab1;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.makhnovetc.ifmo.soap.lab1.ThrottlingBean")
public class ThrottlingException extends Exception {
   // private static final long serialVersionUID = -6647544772732631047L;
    private final ThrottlingBean throttlingBean;

    public ThrottlingException(String msg, ThrottlingBean throttlingBean){
        super(msg);
        this.throttlingBean = throttlingBean;
    }

    public ThrottlingException(String msg, ThrottlingBean throttlingBean,Throwable cause){
        super(msg,cause);
        this.throttlingBean = throttlingBean;
    }

    public ThrottlingBean getFaultInfo() {
        return throttlingBean;
    }
}
