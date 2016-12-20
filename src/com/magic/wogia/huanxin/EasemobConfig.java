package com.magic.wogia.huanxin;


public class EasemobConfig {
	
	public static EasemobConfig instance;
	// 廖臻环信
//	public String CLIENT_ID = "YXA6zazXgHo5EeaDMx1_no_xRQ";
//	public String CLIENT_SECRET = "YXA6yqAgkizmln8YrpA8bfxlC2TiZGU";
//	public String APP_KEY = "liaoinstan/wogiadevelop";
	// WOGIA 环信
	public String CLIENT_ID = "YXA6K4M34IPDEeaQJ_0-J7zctA";
	public String CLIENT_SECRET = "YXA60fp97L3UTWbJaCfb-ETaGxScYW0";
	public String APP_KEY = "1129160926115776/wogia";
	public String _SERVICE = "https://a1.easemob.com/" + APP_KEY + "/";
	public String TOKEN_ID = "";
	
	public static EasemobConfig getInstance(){
		if(instance == null){
			instance = new EasemobConfig();
		}
		return instance;
	}

	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}

	public String getCLIENT_SECRET() {
		return CLIENT_SECRET;
	}

	public void setCLIENT_SECRET(String cLIENT_SECRET) {
		CLIENT_SECRET = cLIENT_SECRET;
	}

	public String getTOKEN_ID() {
		return TOKEN_ID;
	}

	public void setTOKEN_ID(String tOKEN_ID) {
		TOKEN_ID = tOKEN_ID;
	}

	public String getAPP_KEY() {
		return APP_KEY;
	}

	public void setAPP_KEY(String aPP_KEY) {
		APP_KEY = aPP_KEY;
	}

	public String get_SERVICE() {
		return _SERVICE;
	}

	public void set_SERVICE(String _SERVICE) {
		this._SERVICE = _SERVICE;
	}
}