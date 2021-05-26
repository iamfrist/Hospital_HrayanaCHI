package com.imf.haryanachi.networkModel.vitals;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("pname")
    private String pname;

    @SerializedName("p_age")
    private String pAge;

    @SerializedName("patients_status")
    private String patientsStatus;

    @SerializedName("pgender")
    private String pgender;

    @SerializedName("dayscount")
    private String dayscount;

    public String getPname() {
        return pname;
    }

    public String getPAge() {
        return pAge;
    }

    public String getPatientsStatus() {
        return patientsStatus;
    }

    public String getPgender() {
        return pgender;
    }

	public String getDayscount() {
		return dayscount;
	}
}