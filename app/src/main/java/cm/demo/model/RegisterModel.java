package cm.demo.model;

public class RegisterModel extends CMBaseModel {

	enum REGISTER_TYPE {
		REGISTER_TYPE_NORMAL, REGISTER_TYPE_WECHAT, REGISTER_TYPE_QQ,
	}

	private String name;
	private String phoneNo;
	private String confirmCodeFromMobile;
	private String password;
	private REGISTER_TYPE registerType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getConfirmCodeFromMobile() {
		return confirmCodeFromMobile;
	}

	public void setConfirmCodeFromMobile(String confirmCodeFromMobile) {
		this.confirmCodeFromMobile = confirmCodeFromMobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public REGISTER_TYPE getRegisterType() {
		return registerType;
	}

	public void setRegisterType(REGISTER_TYPE registerType) {
		this.registerType = registerType;
	};
}
