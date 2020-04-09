package com.hcb.newBilly.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("ForgetPasswordDTO")
public class ForgetPasswordDTO {
    private String userAccount;
    private String newPassword;
    private String question;
    private String answer;
}
