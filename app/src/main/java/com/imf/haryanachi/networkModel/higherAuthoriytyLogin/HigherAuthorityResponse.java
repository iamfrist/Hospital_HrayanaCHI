package com.imf.haryanachi.networkModel.higherAuthoriytyLogin;

import com.google.gson.annotations.SerializedName;

public class HigherAuthorityResponse{

	@SerializedName("role")
	private String role;

	@SerializedName("others_id")
	private String othersId;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public String getRole(){
		return role;
	}

	public String getOthersId(){
		return othersId;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}