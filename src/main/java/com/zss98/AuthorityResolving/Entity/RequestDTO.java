package com.zss98.AuthorityResolving.Entity;

import lombok.Data;

@Data
public class RequestDTO {
    private int id;
    private String userName = "";
    private String password = "";
    private String phone = "";
    private String email = "";
}
