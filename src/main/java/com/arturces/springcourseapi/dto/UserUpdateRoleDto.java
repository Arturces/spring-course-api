package com.arturces.springcourseapi.dto;

import com.arturces.springcourseapi.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserUpdateRoleDto {

    @NotNull(message = "Role required")
    private Role role;

}
