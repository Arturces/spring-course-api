package com.arturces.springcourseapi.domain;

import com.arturces.springcourseapi.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Request {

    private Long id;
    private String subject;
    private String description;
    private Date creationDate;

    private RequestState state;

    private User user;

    private List<RequestStage> stages = new ArrayList<RequestStage>();

}
