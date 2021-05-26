package com.imf.haryanachi.networkModel.status;

public class StatusResponse{
	private String message;
	private String status;
	private String center_name;
	private String ward_name;


	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}

	public String getCenter_name() {
		return center_name;
	}

	public String getWard_name() {
		return ward_name;
	}
}
