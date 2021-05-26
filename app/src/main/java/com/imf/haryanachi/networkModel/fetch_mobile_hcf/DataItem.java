package com.imf.haryanachi.networkModel.fetch_mobile_hcf;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("district_id")
	private String districtId;

	@SerializedName("chc_id")
	private String chcId;

	@SerializedName("hcfname")
	private String hcfname;

	public String getDistrictId(){
		return districtId;
	}

	public String getChcId(){
		return chcId;
	}

	public String getHcfname(){
		return hcfname;
	}


	@Override
	public String toString() {
		return hcfname;
	}
}