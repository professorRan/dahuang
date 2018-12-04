package net.tsingk.m.impl;

import net.tsingk.m.ISmsAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class NeteaseSmsService implements ISmsAPI {


    @Value("sms.app.id")
    private String smsAppId;

    @Value("sms.app.secret")
    private String smsSecret;

    @Value("sms.app.signature")
    private String smsSignature;

    @Override
    public String sendSMS(String phone, String tplName) {
        //TODO generate random code
        String code = "";

        //TODO send sms by template
        return code;
    }
}
