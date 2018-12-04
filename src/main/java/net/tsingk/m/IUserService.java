package net.tsingk.m;

import net.tsingk.pojo.User;
import net.tsingk.pojo.UserToken;

public interface IUserService {


    public User fetchUserByToken(String token);


    public User fetchUserByUserId(Long userId);


    public UserToken generateTokenFromUser(User user);


    public User regUser(String phone, String password, String nickName) throws UserExistException, IMRegiserException;
}
