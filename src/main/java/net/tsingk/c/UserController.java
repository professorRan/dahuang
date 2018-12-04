package net.tsingk.c;

import net.tsingk.c.resp.SmsResponse;
import net.tsingk.c.resp.UserResponse;
import net.tsingk.m.IMRegiserException;
import net.tsingk.m.ISmsAPI;
import net.tsingk.m.IUserService;
import net.tsingk.pojo.User;
import net.tsingk.m.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/v2/user")
public class UserController {

    @Autowired
    private IUserService ius;


    @Autowired
    private ISmsAPI smsapi;


    @RequestMapping("/reg")
    public UserResponse regUser(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("sign") String sign,
                                @RequestParam("user_code") String code,
                                @RequestParam("nick_name") String nickname) {
        UserResponse ur = new UserResponse();

        //TODO check code

        //TODO check sign

        User user = null;
        try {
            user = ius.regUser(username, password, nickname);
            ur.setError(ReponseError.SUCCESS);
            ur.setResult(ResponseResult.SUCCESS);
            ur.setUser(user);
        } catch (UserExistException e) {
            e.printStackTrace();
            ur.setError(ReponseError.SUCCESS);
            ur.setResult(ResponseResult.ERR_USER_EXIST);
        } catch (IMRegiserException e) {
            e.printStackTrace();
            ur.setError(ReponseError.SUCCESS);
            ur.setResult(ResponseResult.ERR_USER_EXIST);
        }
        return ur;
    }



    @RequestMapping("/send_sms")
    public SmsResponse sendSms(@RequestParam("phone") String phone, @RequestParam("type") int type, @RequestParam("sign") String sign) {
        SmsResponse smsResp = new SmsResponse();

        String tplName="SMS_127185058";
        String code = smsapi.sendSMS(phone, tplName);
        smsResp.setError(ReponseError.SUCCESS);
        smsResp.setResult(ResponseResult.SUCCESS);
        smsResp.setCode(code);
        return smsResp;
    }
}
