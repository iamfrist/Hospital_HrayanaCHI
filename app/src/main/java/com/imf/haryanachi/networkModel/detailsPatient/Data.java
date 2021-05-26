package com.imf.haryanachi.networkModel.detailsPatient;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("systolic_bp")
	private String systolicBp;

	@SerializedName("visit_time")
	private String visitTime;

	@SerializedName("spo2")
	private String spo2;

	@SerializedName("rate")
	private String rate;

	@SerializedName("patients_status")
	private String patientsStatus;

	@SerializedName("temperature")
	private String temperature;

	@SerializedName("pulse")
	private String pulse;

	@SerializedName("diastolic_bp")
	private String diastolicBp;

	@SerializedName("visit_date")
	private String visitDate;

	public String getSystolicBp(){
		return systolicBp;
	}

	public String getVisitTime(){
		return visitTime;
	}

	public String getSpo2(){
		return spo2;
	}

	public String getRate(){
		return rate;
	}

	public String getPatientsStatus(){
		return patientsStatus;
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

	public String getVisitDate(){
		return visitDate;
	}
}