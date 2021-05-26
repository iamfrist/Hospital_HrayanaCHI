package com.imf.haryanachi.networkModel.history;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("systolic_bp")
	private String systolicBp;

	@SerializedName("spo2")
	private String spo2;

	@SerializedName("rate")
	private String rate;

	@SerializedName("pname")
	private String pname;

	@SerializedName("patients_status")
	private String patientsStatus;

	@SerializedName("familyaddress")
	private String familyaddress;

	@SerializedName("temperature")
	private String temperature;

	@SerializedName("pulse")
	private String pulse;

	@SerializedName("diastolic_bp")
	private String diastolicBp;

	public String getSystolicBp(){
		return systolicBp;
	}

	public String getSpo2(){
		return spo2;
	}

	public String getRate(){
		return rate;
	}

	public String getPname(){
		return pname;
	}

	public String getPatientsStatus(){
		return patientsStatus;
	}

	public String getFamilyaddress(){
		return familyaddress;
	}

	public String getTemperature(){
		return temperature;
	}

	public String getPulse(){
		return pulse;
	}

	public String getDiastolicBp(){
		return diastolicBp;
	}
}