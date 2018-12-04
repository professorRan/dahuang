package net.tsingk.persist;

import net.tsingk.pojo.User;
import net.tsingk.pojo.UserToken;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserPersist {


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "nickName", column = "NICK_NAME"),
            @Result(property = "userName", column = "phone_number"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "avatarVersion", column = "avatar_version"),
            @Result(property = "openId", column = "open_id"),
            @Result(property = "imUserId", column = "im_user_id")

            })
    @Select("SELECT u.id, u.NAME, u.GENDER FROM HYP_USER u left jon HYP_TOKEN ht on ht.TOKEN_USER_ID ON u.id WHERE ht.token=#{token} and ht.EXPIRED_TIME > #{currentTime}")
    User selectUserByToken(String token, long currentTime);

    @Select("SELECT id, NAME FROM HYP_USER WHERE id=#{id}")
    User selectUserById(long id);

    @Select("SELECT id, NAME FROM HYP_USER WHERE NAME=#{name}")
    User selectUserByName(String name);


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "nickName", column = "NICK_NAME"),
            @Result(property = "userName", column = "phone_number"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "avatarVersion", column = "avatar_version"),
            @Result(property = "openId", column = "open_id"),
            @Result(property = "imUserId", column = "im_user_id")

    })
    @Select("SELECT u.id,  u.GENDER, u.NICK_NAME, u.PHONE_NUMBER, u.GENDER, u.AVATAR_VERSION FROM HYP_USER u  WHERE  u.USER_NAME=#{userName}")
    User selectUserByUserName(String userName);

    @Select("SELECT id, NAME FROM HYP_USER WHERE NAME LIKE concat('%',#{name},'%')")
    List<User> selectUsers(String name);


    @Insert("INSERT INTO HYP_USER(NICK_NAME, PHONE_NUMBER, USER_PASSWORD, AVATAR_VERSION) VALUES (#{user.nickName}, #{user.userName}, #{user.userPass},#{user.avatar_version}) ")
    void insertUser(User user);


    @Insert("INSERT INTO HYP_TOKEN(TOKEN, TOKEN_TYPE, TOEKN_USER_ID, GENERATED_TIME, GENERATED_CTIME, EXPIRED_TIME, EXPIRED_CTIME, IM_TOKEN, OPEN_ID) " +
            "VALUES (#{token.token}, #{token.type}, #{token.userId}, #{token.gtime}, #{token.gctime}, #{token.etime}, #{token.ectime}, #{token.imToken}, #{token.openId}) ")

    void insertUserToken(UserToken token);
}
