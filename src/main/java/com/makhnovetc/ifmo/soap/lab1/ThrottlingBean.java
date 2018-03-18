package com.makhnovetc.ifmo.soap.lab1;

public class ThrottlingBean {
    private static final String DEFAULT_MESSAGE = "throttling exception";
    protected String message;
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public static ThrottlingBean defaultInstance(){
        ThrottlingBean fault = new ThrottlingBean();
        fault.message = DEFAULT_MESSAGE;
        return fault;
    }
}
