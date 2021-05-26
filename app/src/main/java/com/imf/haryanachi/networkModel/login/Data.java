package com.imf.haryanachi.networkModel.login;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("doctor_id")
    private String doctorId;

    @SerializedName("phc_id")
    private String phcId;

    @SerializedName("chc_id")
    private String chcId;

    @SerializedName("doctor_name")
    private String doctorName;

    @SerializedName("team_members")
    private String teamMember;

    @SerializedName("centername")
    private String centername;

    @SerializedName("wardname")
    private String wardname;

    @SerializedName("district_name")
    private String district_name;

    @SerializedName("district_id")
    private String district_id;


    public String getDistrict_name() {
        return district_name;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPhcId() {
        return phcId;
    }

    public String getChcId() {
        return chcId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getTeamMember() {
        return teamMember;
    }

    public String getCentername() {
        return centername;
    }

    public String getWardname() {
        return wardname;
    }

    public String getDistrict_id() {
        return district_id;
    }
}