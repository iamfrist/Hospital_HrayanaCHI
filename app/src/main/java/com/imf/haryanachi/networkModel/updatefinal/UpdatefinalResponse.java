package com.imf.haryanachi.networkModel.updatefinal;

import com.google.gson.annotations.SerializedName;

public class UpdatefinalResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}
}