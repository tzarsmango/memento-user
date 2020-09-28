package com.codebenders.mementouser.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel {
    private String firstName;
    private String lastName;
    private String email;
}
