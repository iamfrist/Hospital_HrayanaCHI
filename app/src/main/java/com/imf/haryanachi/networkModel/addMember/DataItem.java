package com.imf.haryanachi.networkModel.addMember;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("password")
	private String password;

	@SerializedName("comorbidities")
	private String comorbidities;

	@SerializedName("familymember")
	private String familymember;

	@SerializedName("familyaddress")
	private String familyaddress;

	@SerializedName("phc_id")
	private String phcId;

	@SerializedName("familystate")
	private String familystate;

	@SerializedName("district_id")
	private String districtId;

	@SerializedName("chc_id")
	private String chcId;

	@SerializedName("positivemember")
	private String positivemember;

	@SerializedName("familycity")
	private String familycity;

	@SerializedName("holdermobile")
	private String holdermobile;

	@SerializedName("familyholder")
	private String familyholder;

	public String getPassword(){
		return password;
	}

	public String getComorbidities(){
		return comorbidities;
	}

	public String getFamilymember(){
		return familymember;
	}

	public String getFamilyaddress(){
		return familyaddress;
	}

	public String getPhcId(){
		return phcId;
	}

	public String getFamilystate(){
		return familystate;
	}

	public String getDistrictId(){
		return districtId;
	}

	public String getChcId(){
		return chcId;
	}

	public String getPositivemember(){
		return positivemember;
	}

	public String getFamilycity(){
		return familycity;
	}

	public String getHoldermobile(){
		return holdermobile;
	}

	public String getFamilyholder(){
		return familyholder;
	}
}