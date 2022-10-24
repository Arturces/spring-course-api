package com.arturces.springcourseapi.dto;


import com.arturces.springcourseapi.domain.Request;
import com.arturces.springcourseapi.domain.RequestStage;
import com.arturces.springcourseapi.domain.User;
import com.arturces.springcourseapi.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestStageSaveDto {
    private String description;

    @NotNull(message = "State required")
    private RequestState state;

    @NotNull(message = "Request required")
    private Request request;

    @NotNull(message = "Owner required")
    private User owner;

    public RequestStage transformToRequestStage(){
        RequestStage stage = new RequestStage(null, description, null, state, request, owner);
        return stage;
    }
}
