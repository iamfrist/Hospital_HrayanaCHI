package com.imf.haryanachi.networkModel.fetch.fetch_phc;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("phc_name")
	private String phcName;

	@SerializedName("phc_id")
	private String phcId;

	public String getPhcName(){
		return phcName;
	}

	public String getPhcId(){
		return phcId;
	}


	@Override
	public String toString() {
		return phcName;
	}
}