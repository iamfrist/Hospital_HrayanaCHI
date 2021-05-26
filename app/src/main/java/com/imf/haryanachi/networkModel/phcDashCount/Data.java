package com.imf.haryanachi.networkModel.phcDashCount;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("finaldischg")
	private int finaldischg;

	@SerializedName("all_positive")
	private int allPositive;

	@SerializedName("familiescount")
	private int familiescount;

	@SerializedName("finalhospital")
	private int finalhospital;

	public int getFinaldischg(){
		return finaldischg;
	}

	public int getAllPositive(){
		return allPositive;
	}

	public int getFamiliescount(){
		return familiescount;
	}

	public int getFinalhospital(){
		return finalhospital;
	}
}