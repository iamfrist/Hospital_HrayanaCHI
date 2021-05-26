package com.imf.haryanachi.networkModel.all_state_count_api;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("overfamily")
	private int overfamily;

	@SerializedName("membercount")
	private int membercount;

	@SerializedName("notvisited")
	private int notvisited;

	@SerializedName("dischargeddcount")
	private int dischargeddcount;

	@SerializedName("phccount")
	private int phccount;

	@SerializedName("comorbcount")
	private int comorbcount;

	@SerializedName("uniqueadmit")
	private int uniqueadmit;

	@SerializedName("admitcount")
	private int admitcount;

	@SerializedName("partialcenter")
	private int partialcenter;

	@SerializedName("expiredcount")
	private int expiredcount;

	@SerializedName("lastcount")
	private int lastcount;

	@SerializedName("fullfamily")
	private int fullfamily;

	@SerializedName("vcriticalcount")
	private int vcriticalcount;

	@SerializedName("stablecount")
	private int stablecount;

	@SerializedName("critcalcount")
	private int critcalcount;

	@SerializedName("districtname")
	private String districtname;

	@SerializedName("admitlengthcount")
	private int admitlengthcount;

	@SerializedName("hospitalizedcount")
	private int hospitalizedcount;

	@SerializedName("district_id")
	private String districtId;

	@SerializedName("hcfcount")
	private int hcfcount;

	@SerializedName("vacantbedcount")
	private int vacantbedcount;

	public int getOverfamily(){
		return overfamily;
	}

	public int getMembercount(){
		return membercount;
	}

	public int getNotvisited(){
		return notvisited;
	}

	public int getDischargeddcount(){
		return dischargeddcount;
	}

	public int getPhccount(){
		return phccount;
	}

	public int getComorbcount(){
		return comorbcount;
	}

	public int getUniqueadmit(){
		return uniqueadmit;
	}

	public int getAdmitcount(){
		return admitcount;
	}

	public int getPartialcenter(){
		return partialcenter;
	}

	public int getExpiredcount(){
		return expiredcount;
	}

	public int getLastcount(){
		return lastcount;
	}

	public int getFullfamily(){
		return fullfamily;
	}

	public int getVcriticalcount(){
		return vcriticalcount;
	}

	public int getStablecount(){
		return stablecount;
	}

	public int getCritcalcount(){
		return critcalcount;
	}

	public String getDistrictname(){
		return districtname;
	}

	public int getAdmitlengthcount(){
		return admitlengthcount;
	}

	public int getHospitalizedcount(){
		return hospitalizedcount;
	}

	public String getDistrictId(){
		return districtId;
	}

	public int getHcfcount(){
		return hcfcount;
	}

	public int getVacantbedcount(){
		return vacantbedcount;
	}
}