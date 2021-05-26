package com.imf.haryanachi.networkModel.hopiList;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("hospital_id")
	private String hospitalId;

	@SerializedName("hospital_name")
	private String hospitalName;

	public String getHospitalId(){
		return hospitalId;
	}

	public String getHospitalName(){
		return hospitalName;
	}
}