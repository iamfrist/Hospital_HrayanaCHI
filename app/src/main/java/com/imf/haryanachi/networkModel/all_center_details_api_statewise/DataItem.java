package com.imf.haryanachi.networkModel.all_center_details_api_statewise;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("overfamily")
	private int overfamily;

	@SerializedName("membercount")
	private int membercount;

	@SerializedName("dischargeddcount")
	private int dischargeddcount;

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

	@SerializedName("center_id")
	private String centerId;

	@SerializedName("critcalcount")
	private int critcalcount;

	@SerializedName("admitlengthcount")
	private int admitlengthcount;

	@SerializedName("district_Id")
	private String districtId;

	@SerializedName("vacantbedcount")
	private int vacantbedcount;

	@SerializedName("hcfname")
	private String hcfname;

	public int getOverfamily(){
		return overfamily;
	}

	public int getMembercount(){
		return membercount;
	}

	public int getDischargeddcount(){
		return dischargeddcount;
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

	public String getCenterId(){
		return centerId;
	}

	public int getCritcalcount(){
		return critcalcount;
	}

	public int getAdmitlengthcount(){
		return admitlengthcount;
	}

	public String getDistrictId(){
		return districtId;
	}

	public int getVacantbedcount(){
		return vacantbedcount;
	}

	public String getHcfname(){
		return hcfname;
	}
}