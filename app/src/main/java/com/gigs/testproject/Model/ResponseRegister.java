package com.gigs.testproject.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseRegister {

	@SerializedName("data")
	private Object data;

	@SerializedName("errorMessage")
	private Object errorMessage;

	@SerializedName("message")
	private String message;

	@SerializedName("statusCode")
	private int statusCode;

	public Object getData(){
		return data;
	}

	public Object getErrorMessage(){
		return errorMessage;
	}

	public String getMessage(){
		return message;
	}

	public int getStatusCode(){
		return statusCode;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLogin{" + 
			"data = '" + data + '\'' + 
			",errorMessage = '" + errorMessage + '\'' + 
			",message = '" + message + '\'' + 
			",statusCode = '" + statusCode + '\'' + 
			"}";
		}
}