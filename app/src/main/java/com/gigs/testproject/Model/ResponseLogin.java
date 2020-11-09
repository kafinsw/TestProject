package com.gigs.testproject.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("data")
	private Data data;

	@SerializedName("errorMessage")
	private Object errorMessage;

	@SerializedName("message")
	private String message;

	@SerializedName("statusCode")
	private int statusCode;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setErrorMessage(Object errorMessage){
		this.errorMessage = errorMessage;
	}

	public Object getErrorMessage(){
		return errorMessage;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
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