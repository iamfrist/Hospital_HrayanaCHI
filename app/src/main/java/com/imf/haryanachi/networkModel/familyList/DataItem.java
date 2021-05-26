package com.imf.haryanachi.networkModel.familyList;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("positivememeber")
	private String positivememeber;

	@SerializedName("comorbidities")
	private String comorbidities;

	@SerializedName("visit_time")
	private String visitTime;

	@SerializedName("familymember")
	private String familymember;

	@SerializedName("familyholder")
	private String familyholder;

	@SerializedName("visit_date")
	private String visitDate;

	public String getPositivememeber(){
		return positivememeber;
	}

	public String getComorbidities(){
		return comorbidities;
	}

	public String getVisitTime(){
		return visitTime;
	}

	public String getFamilymember(){
		return familymember;
	}

	public String getFamilyholder(){
		return familyholder;
	}

	public String getVisitDate(){
		return visitDate;
	}

	@Override
	public String toString() {
		return familyholder;
	}
}