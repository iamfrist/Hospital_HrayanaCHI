package com.imf.haryanachi.networkModel.patientLogin;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("comorbidities")
	private String comorbidities;

	@SerializedName("familymember")
	private String familymember;

	@SerializedName("familyname")
	private String familyname;

	@SerializedName("familystate")
	private String familystate;

	@SerializedName("pnumber")
	private String pnumber;

	@SerializedName("patientsaddress")
	private String patientsaddress;

	@SerializedName("positivemember")
	private String positivemember;

	@SerializedName("familycity")
	private String familycity;

	public String getComorbidities(){
		return comorbidities;
	}

	public String getFamilymember(){
		return familymember;
	}

	public String getFamilyname(){
		return familyname;
	}

	public String getFamilystate(){
		return familystate;
	}

	public String getPnumber(){
		return pnumber;
	}

	public String getPatientsaddress(){
		return patientsaddress;
	}

	public String getPositivemember(){
		return positivemember;
	}

	public String getFamilycity(){
		return familycity;
	}
}