package com.hcb.newBilly.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class LoginDTO {
    private String userAccount;
    private String password;
}
