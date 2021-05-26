package com.imf.haryanachi.networkModel.patientLogin;

import com.google.gson.annotations.SerializedName;

public class PatientsdataItem {

    @SerializedName("pname")
    private String pname;

    @SerializedName("p_age")
    private String pAge;

    @SerializedName("qrcode")
    private String qrcode;

    @SerializedName("pnumber")
    private String pnumber;

    @SerializedName("patients_id")
    private String patientsId;

    @SerializedName("pgender")
    private String pgender;
    @SerializedName("regno")
    private String regno;

	public String getpAge() {
		return pAge;
	}

	public String getRegno() {
		return regno;
	}

	public String getPname() {
        return pname;
    }

    public String getPAge() {
        return pAge;
    }

    public String getQrcode() {
        return qrcode;
    }

    public String getPnumber() {
        return pnumber;
    }

    public String getPatientsId() {
        return patientsId;
    }

    public String getPgender() {
        return pgender;
    }
}