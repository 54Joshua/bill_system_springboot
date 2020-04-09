package com.hcb.newBilly.serviceImp;

import com.hcb.newBilly.common.BillyException;
import com.hcb.newBilly.common.IdCreator;
import com.hcb.newBilly.dao.UserDao;
import com.hcb.newBilly.dto.ForgetPasswordDTO;
import com.hcb.newBilly.dto.SaveUserDTO;
import com.hcb.newBilly.dto.UpdatePasswordDTO;
import com.hcb.newBilly.po.ProblemPO;
import com.hcb.newBilly.po.UserPO;
import com.hcb.newBilly.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;

@Service
public class UserSerivceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(rollbackFor = BillyException.class)
    public int saveUser(SaveUserDTO saveUserDTO) {
        UserPO userAccount = userDao.getUserByUserAccount(saveUserDTO.getUserAccount());
        if (null != userAccount) {
            throw new BillyException("用户【" + saveUserDTO.getUserAccount() + "】已存在");
        }
        String userId = IdCreator.getNextId();
        UserPO userPO = new UserPO();
        userPO.setUserId(userId);
        userPO.setUserAccount(saveUserDTO.getUserAccount());
        SimpleHash md5 = new SimpleHash("md5", saveUserDTO.getPassword());
        String password = md5.toHex();
        userPO.setPassword(password);
        userPO.setUserName(saveUserDTO.getUserName());
        userPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        userDao.saveUser(userPO);
        ProblemPO problemPO = new ProblemPO();
        problemPO.setPcId(IdCreator.getNextId());
        problemPO.setAnswer(saveUserDTO.getAnswer());
        problemPO.setUserId(userId);
        problemPO.setProblemId(saveUserDTO.getProblemId());
        userDao.saveAnswer(problemPO);
        return 0;
    }

    @Override
    public void forgetPasswordDTO(ForgetPasswordDTO forgetPasswordDTO) {
        UserPO userPO = userDao.getUserByUserAccount(forgetPasswordDTO.getUserAccount());
        if (null == userPO) {
            throw new BillyException("用户【" + forgetPasswordDTO.getUserAccount() + "】不存在");
        }
        String answer = userDao.getAnswer(userPO.getUserId(), forgetPasswordDTO.getQuestion());
        if (!forgetPasswordDTO.getAnswer().equals(answer)) {
            throw new BillyException("验证答案错误");
        }
        SimpleHash md5 = new SimpleHash("md5", forgetPasswordDTO.getNewPassword());
        String password = md5.toHex();
        userDao.updatePassword(forgetPasswordDTO.getUserAccount(), password);
    }

    @Override
    public int updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        UserPO userPO = userDao.getUserByUserAccount(updatePasswordDTO.getUserAccount());
        if (null == userPO) {
            throw new BillyException("用户【" + updatePasswordDTO.getUserAccount() + "】不存在");
        }
        SimpleHash md5 = new SimpleHash("md5");
        md5.setIterations(2);
        md5.setBytes(updatePasswordDTO.getOldPassword().getBytes());
        String password = md5.toHex();
        if (!userPO.getPassword().equals(password)) {
            throw new BillyException("密码错误");
        }
        userDao.updatePassword(updatePasswordDTO.getUserAccount(), updatePasswordDTO.getNewPassword());
        return 0;

    }

    @Override
    public UserPO getUserPOByUserAccount(String userAccount) {
        UserPO userPO = userDao.getUserByUserAccount(userAccount);
        return userPO;
    }
}
