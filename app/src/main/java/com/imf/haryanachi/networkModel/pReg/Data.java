package com.imf.haryanachi.networkModel.pReg;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("patients_id")
    private String patientsId;
    @SerializedName("regno")
    private String regno;
    @SerializedName("holdermobile")
    private String holdermobile;

    public String getPatientsId() {
        return patientsId;
    }


	public String getRegno() {
		return regno;
	}

	public String getHoldermobile() {
		return holdermobile;
	}
}