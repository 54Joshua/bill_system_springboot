package com.hcb.newBilly.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("SaveUserDTO")
public class SaveUserDTO {
    private String userAccount;
    private String userName;
    private String password;
    private String problemId;
    private String answer;
}



