package com.sarayeva.cybercubebetask.exception;

public class AnalysisNotFoundException extends RuntimeException{

    public AnalysisNotFoundException(){
        super("Analysis not found");
    }
}
