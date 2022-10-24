package com.arturces.springcourseapi.dto;

import com.arturces.springcourseapi.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRoleDto {
    private Role role;

}
