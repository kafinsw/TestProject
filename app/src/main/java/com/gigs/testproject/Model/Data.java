package com.gigs.testproject.Model;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("token")
	private String token;

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"token = '" + token + '\'' + 
			"}";
		}
}