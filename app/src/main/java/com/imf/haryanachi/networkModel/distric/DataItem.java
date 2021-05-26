package com.imf.haryanachi.networkModel.distric;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("district_name")
	private String districtName;

	@SerializedName("district_id")
	private String districtId;

	public String getDistrictName(){
		return districtName;
	}

	public String getDistrictId(){
		return districtId;
	}


	@Override
	public String toString() {
		return districtName;
	}
}