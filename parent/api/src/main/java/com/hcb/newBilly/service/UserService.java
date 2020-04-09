package com.hcb.newBilly.service;

import com.hcb.newBilly.dto.ForgetPasswordDTO;
import com.hcb.newBilly.dto.SaveUserDTO;
import com.hcb.newBilly.dto.UpdatePasswordDTO;
import com.hcb.newBilly.po.DictionaryPO;
import com.hcb.newBilly.po.UserPO;

import java.util.List;

public interface UserService {


    int saveUser(SaveUserDTO saveUserDTO);

    void forgetPasswordDTO(ForgetPasswordDTO forgetPasswordDTO);

    int updatePassword(UpdatePasswordDTO updatePasswordDTO);

    UserPO getUserPOByUserAccount(String userAccount);
}
