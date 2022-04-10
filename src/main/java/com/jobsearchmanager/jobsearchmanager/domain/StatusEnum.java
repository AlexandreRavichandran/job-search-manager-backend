package com.jobsearchmanager.jobsearchmanager.domain;

public enum StatusEnum {
    GOING_TO_APPLY("Going_to_apply"),
    APPLIED("applied"),
    RELAUNCH("relaunch"),
    HAVE_A_MEETING("have_a_meeting");

    private String label;

    StatusEnum(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}
