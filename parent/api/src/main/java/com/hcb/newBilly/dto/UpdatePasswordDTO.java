package com.hcb.newBilly.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class UpdatePasswordDTO {
    private String userAccount;
    private String oldPassword;
    private String newPassword;
}
