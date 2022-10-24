package com.arturces.springcourseapi.dto;

import com.arturces.springcourseapi.domain.Request;
import com.arturces.springcourseapi.domain.RequestStage;
import com.arturces.springcourseapi.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestSaveDto {

    @NotBlank(message = "Subject required!")
    private String subject;
    private String description;

    @NotNull(message = "Owner required")
    private User owner;
    private List<RequestStage> stages = new ArrayList<RequestStage>();

    public Request transformToRequest(){
        Request request = new Request(null, this.subject,this.description, null,null,this.owner,stages);
    return request;
    }
}