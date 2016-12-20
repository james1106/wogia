package com.magic.wogia.util;

/**
 * 通用返回类型.
 * @author 
 *
 */
public class ResponseEntity {

	/**
	 * 错误码.
	 */
	private int status;

	/**
	 * 错误信息.
	 */
	private String detail = ErrorMessage.Fail;

	/**
	 * 返回数据.
	 */
	private Object data;

	public ResponseEntity() {

	}

	public ResponseEntity(Object data){
		this.data = data;
	}

	public ResponseEntity(int status, String detail) {
		this.status = status;
		this.detail = detail;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

