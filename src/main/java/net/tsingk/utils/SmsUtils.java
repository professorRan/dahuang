package net.tsingk.utils;

/**
 * 短信发送工具类
 * 
 */
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SmsUtils {
	// 产品名称
	static final String product = "Dysmsapi";
	// 产品域名
	static final String domain = "dysmsapi.aliyuncs.com";

	// 输入自己的AK
	static final String accessKeyId = "LTAIbQuTgEdFrOEF";
	static final String accessKeySecret = "uPJyLSHgQNHOTr4VbN10SYygOu9Qnq";

	public static String sendSms(String telephone, String tplName, String code) throws ClientException {

		//自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(telephone);
		// 必填:短信签名
		request.setSignName("花一派");
		// 必填:短信模板
		request.setTemplateCode(tplName);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
		
		request.setTemplateParam("{\"code\":\"" + code + "\"}");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		//request.setOutId("");

		// 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = null;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			System.out.println("短信发送成功");
		} else {
			System.out.println("短信发送失败");
		}

		return null;

	}

	//获取随机4位验证码
	private static int newcode;

	public static int getNewcode() {
		return newcode;
	}

	public static void setNewcode() {
		newcode = (int) (Math.random() * 9999) + 100;// 每次调用生成一次四位数的随机数
	}

	
}
