package com.arturces.springcourseapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NotNull
@Getter
@Setter
public class UploadFileModel {

    private String name;
    private String location;
}
