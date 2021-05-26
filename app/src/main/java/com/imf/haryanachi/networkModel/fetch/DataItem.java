package com.imf.haryanachi.networkModel.fetch;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("chc_name")
	private String chcName;

	@SerializedName("chc_id")
	private String chcId;

	public String getChcName(){
		return chcName;
	}

	public String getChcId(){
		return chcId;
	}


	@Override
	public String toString() {
		return chcName;
	}
}