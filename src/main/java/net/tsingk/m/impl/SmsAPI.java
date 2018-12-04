package net.tsingk.m.impl;

import com.aliyuncs.exceptions.ClientException;

import net.tsingk.m.ISmsAPI;
import net.tsingk.utils.SmsUtils;

public class SmsAPI implements ISmsAPI {

	@Override
	public String sendSMS(String phone, String tplName) {
		
		tplName="SMS_127185058";
		//发送短信到用户手机
		if (phone !=null && phone.length() != 0) {
			try {
				SmsUtils.setNewcode();
				String code = Integer.toString(SmsUtils.getNewcode());
				return SmsUtils.sendSms(phone, tplName,code);
				
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	

}
