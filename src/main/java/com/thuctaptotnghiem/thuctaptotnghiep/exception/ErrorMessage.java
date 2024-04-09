package com.thuctaptotnghiem.thuctaptotnghiep.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorMessage {

    private int statusCode;
    private Date timestamp;
    private List<String> message;
    private String description;


}


