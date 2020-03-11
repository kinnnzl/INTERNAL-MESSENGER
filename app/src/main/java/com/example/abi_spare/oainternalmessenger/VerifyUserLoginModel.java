package com.example.abi_spare.oainternalmessenger;

import com.google.gson.annotations.SerializedName;

public class VerifyUserLoginModel{

	@SerializedName("Login")
	private String login;

	@SerializedName("AppDBUser")
	private String appDBUser;

	@SerializedName("AppDBPassword")
	private String appDBPassword;

	public void setLogin(String login){
		this.login = login;
	}

	public String getLogin(){
		return login;
	}

	public void setAppDBUser(String appDBUser){
		this.appDBUser = appDBUser;
	}

	public String getAppDBUser(){
		return appDBUser;
	}

	public void setAppDBPassword(String appDBPassword){
		this.appDBPassword = appDBPassword;
	}

	public String getAppDBPassword(){
		return appDBPassword;
	}
}