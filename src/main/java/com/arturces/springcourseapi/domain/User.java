package com.arturces.springcourseapi.domain;

import com.arturces.springcourseapi.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;

    private Role role;

    private List<Request> requests = new ArrayList<Request>();

    private List<RequestStage> stages = new ArrayList<RequestStage>();
}
