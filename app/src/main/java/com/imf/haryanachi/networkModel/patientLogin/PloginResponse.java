package com.imf.haryanachi.networkModel.patientLogin;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PloginResponse{

	@SerializedName("data")
	private Data data;

	@SerializedName("patientsdata")
	private List<PatientsdataItem> patientsdata;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public Data getData(){
		return data;
	}

	public List<PatientsdataItem> getPatientsdata(){
		return patientsdata;
	}

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}
}