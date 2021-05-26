package com.imf.haryanachi.networkModel.fetch_mobile_phc;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("phc_id")
	private String phcId;

	@SerializedName("district_id")
	private String districtId;

	@SerializedName("chc_id")
	private String chcId;

	@SerializedName("phcname")
	private String phcname;

	public String getPhcId(){
		return phcId;
	}

	public String getDistrictId(){
		return districtId;
	}

	public String getChcId(){
		return chcId;
	}

	public String getPhcname(){
		return phcname;
	}

	@Override
	public String toString() {
		return phcname;
	}
}