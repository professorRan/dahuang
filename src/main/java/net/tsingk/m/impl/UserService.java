package net.tsingk.m.impl;

import net.tsingk.m.*;
import net.tsingk.persist.IUserPersist;
import net.tsingk.pojo.IMUser;
import net.tsingk.pojo.User;
import net.tsingk.pojo.UserToken;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.util.Date;


@Service
public class UserService implements IUserService {

    @Autowired
    private ISecurityService ss;

    @Autowired
    private IUserPersist up;

    @Autowired
    private NeteaseService ns;

    @Override
    public User fetchUserByToken(String token) {
        try {
            if (ss.verifyToken(token) == false) {
                return null;
            }
            User user = up.selectUserByToken(token, System.currentTimeMillis() / 1000);
            return user;
        } catch (SignException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User fetchUserByUserId(Long userId) {
        return null;
    }

    @Override
    public UserToken generateTokenFromUser(User user) {
        UserToken token = new UserToken();
        JSONObject obj = new JSONObject();
        obj.put("number", user.getUserName());
        obj.put("uid", user.getId());
        obj.put("im_token", user.getToken());
        obj.put("im_id", user.getImOutId());
        obj.put("gtime", System.currentTimeMillis());
        String data = obj.toString();
        data = new  BASE64Encoder().encode(data.getBytes());

        Date date = new Date();
        token.setToken(data);
        token.setUserId(user.getId());
        token.setImId(user.getImUserId());
        token.setImToken(user.getImToken());
        token.setGctime(date);
        token.setGtime(date.getTime() / 1000);
        long etime = date.getTime() + 6 * 30 * 24 * 60 * 60;
        Date edate = new Date(etime * 1000);
        token.setEctime(edate);
        token.setEtime(etime);

        up.insertUserToken(token);
        return token;
    }

    @Override
    public User regUser(String phone, String password, String nickName) throws UserExistException, IMRegiserException {
        User user = new User();

        User cache = up.selectUserByUserName(phone);
        if (cache != null) {
            throw new UserExistException(" User " + phone +" already exist");
        }


        String accId = phone;
        IMUser imuser = ns.createUser(nickName, accId);

        if (imuser == null) {
            throw new IMRegiserException(" im register failed "+ accId);
        }
        user.setImUserId(imuser.getImUserId());;
        user.setAvatarVersion(System.currentTimeMillis()+"");
        user.setUserPass(password);
        user.setUserName(phone);
        user.setNickName(nickName);
        user.setImToken(imuser.getImUserToken());
        up.insertUser(user);

        UserToken token = generateTokenFromUser(user);
        user.setToken(token.getToken());
        return user;
    }
}
