package com.arturces.springcourseapi.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiError implements Serializable {

    @Serial
    private static final long serialVersionUID = 4368266265383576751L;
    private int code;
    private String msg;
    private Date date;
    
            
    
}
