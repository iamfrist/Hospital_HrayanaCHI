package com.imf.haryanachi.networkModel.dischaerge;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("visit_time")
	private String visitTime;

	@SerializedName("pname")
	private String pname;

	@SerializedName("p_age")
	private String pAge;

	@SerializedName("pgender")
	private String pgender;

	@SerializedName("visit_date")
	private String visitDate;

	public String getVisitTime(){
		return visitTime;
	}

	public String getPname(){
		return pname;
	}

	public String getPAge(){
		return pAge;
	}

	public String getPgender(){
		return pgender;
	}

	public String getVisitDate(){
		return visitDate;
	}
}