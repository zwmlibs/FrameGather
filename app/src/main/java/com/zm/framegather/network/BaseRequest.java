package com.zm.framegather.network;

import java.io.Serializable;
import java.util.List;

public class BaseRequest<T> implements Serializable{

	/**
	 *   < 0 			程序问题
	 *   0				请求成功且有数据
	 *   >0  and <=5	请求成功，但出了一些可控问题
	 */
	private String code = "-1";
	private String msg;
	private T obj;
	private T user;
	private List<T> list;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	public T getUser() {
		return user;
	}

	public void setUser(T user) {
		this.user = user;
	}
}
