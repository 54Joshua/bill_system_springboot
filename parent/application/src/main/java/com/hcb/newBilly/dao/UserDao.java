package com.hcb.newBilly.dao;

import com.hcb.newBilly.po.ProblemPO;
import com.hcb.newBilly.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    int saveUser(UserPO userPO);

    int updateUser(UserPO userPO);

    int updatePassword(@Param("userAccount") String userAccount, @Param("password") String password);

    UserPO getUserByUserAccount(@Param("userAccount") String userAccount);

    List<UserPO> findUserByUserName(@Param("userName") String userName);

    void saveAnswer(ProblemPO problemPO);

    String getAnswer(@Param("userId") String userId, @Param("problemId") String problemId);

}
