package com.zss98.AuthorityResolving.Entity;

import lombok.Data;

@Data
public class UserData extends UserDTO {
    private String password;
    private String roleName;
}
