package com.imf.haryanachi.networkModel.positiveList;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("visit_time")
	private String visitTime;

	@SerializedName("pname")
	private String pname;

	@SerializedName("dayscount")
	private int dayscount;

	@SerializedName("no_como")
	private String noComo;

	@SerializedName("visit_date")
	private String visitDate;

	public String getVisitTime(){
		return visitTime;
	}

	public String getPname(){
		return pname;
	}

	public int getDayscount(){
		return dayscount;
	}

	public String getNoComo(){
		return noComo;
	}

	public String getVisitDate(){
		return visitDate;
	}



}