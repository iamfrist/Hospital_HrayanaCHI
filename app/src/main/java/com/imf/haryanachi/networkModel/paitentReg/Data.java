package com.imf.haryanachi.networkModel.paitentReg;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("pname")
    private String pname;

    @SerializedName("visit_id")
    private String visitId;

    @SerializedName("patients_id")
    private String patientsId;
    @SerializedName("p_age")
    private String pgae;
    @SerializedName("p_gender")
    private String pgender;

    public String getPname() {
        return pname;
    }

    public String getVisitId() {
        return visitId;
    }

    public String getPatientsId() {
        return patientsId;
    }

    public String getPgae() {
        return pgae;
    }

    public String getPgender() {
        return pgender;
    }
}