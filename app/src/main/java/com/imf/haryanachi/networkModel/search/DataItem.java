package com.imf.haryanachi.networkModel.search;

import com.google.gson.annotations.SerializedName;

public class DataItem {

    @SerializedName("lname")
    private String lname;

    @SerializedName("patient_id")
    private String patientId;

    @SerializedName("patient_name")
    private String patientName;

    @SerializedName("pnumber")
    private String pnumber;
    @SerializedName("cr_no")
    private String cr_no;

    @SerializedName("p_age")
    private String pgae;
    @SerializedName("p_gender")
    private String pgender;

    @SerializedName("holdermobile")
    private String holdermobile;
    @SerializedName("regno")
    private String regno;

    public String getRegno() {
        return regno;
    }

    public String getHoldermobile() {
        return holdermobile;
    }

    public String getCr_no() {
        return cr_no;
    }

    public String getLname() {
        return lname;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPnumber() {
        return pnumber;
    }

    public String getPgae() {
        return pgae;
    }

    public String getPgender() {
        return pgender;
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "lname='" + lname + '\'' +
                ", patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", pnumber='" + pnumber + '\'' +
                '}';
    }
}