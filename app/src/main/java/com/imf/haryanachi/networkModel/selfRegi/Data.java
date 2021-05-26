package com.imf.haryanachi.networkModel.selfRegi;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("lname")
	private String lname;

	@SerializedName("password")
	private String password;

	@SerializedName("comorbidities")
	private String comorbidities;

	@SerializedName("pname")
	private String pname;

	@SerializedName("p_age")
	private String pAge;

	@SerializedName("qrcode")
	private String qrcode;

	@SerializedName("covid_status")
	private String covidStatus;

	@SerializedName("pnumber")
	private String pnumber;

	@SerializedName("patientsaddress")
	private String patientsaddress;

	@SerializedName("registered_date")
	private String registeredDate;

	@SerializedName("pgender")
	private String pgender;

	public String getLname(){
		return lname;
	}

	public String getPassword(){
		return password;
	}

	public String getComorbidities(){
		return comorbidities;
	}

	public String getPname(){
		return pname;
	}

	public String getPAge(){
		return pAge;
	}

	public String getQrcode(){
		return qrcode;
	}

	public String getCovidStatus(){
		return covidStatus;
	}

	public String getPnumber(){
		return pnumber;
	}

	public String getPatientsaddress(){
		return patientsaddress;
	}

	public String getRegisteredDate(){
		return registeredDate;
	}

	public String getPgender(){
		return pgender;
	}
}