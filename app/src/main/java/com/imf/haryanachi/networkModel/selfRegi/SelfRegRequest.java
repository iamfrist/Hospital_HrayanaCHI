package com.imf.haryanachi.networkModel.selfRegi;

import com.imf.haryanachi.networkModel.fetch.ComoList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelfRegRequest {

    String pname,lname,p_age,pgender,covid_status,pnumber,password,patientsaddress,chc_id,phc_id;

    @SerializedName("comorbidities")
    private List<ComoList> data;

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setP_age(String p_age) {
        this.p_age = p_age;
    }

    public void setPgender(String pgender) {
        this.pgender = pgender;
    }

    public void setCovid_status(String covid_status) {
        this.covid_status = covid_status;
    }


    public void setData(List<ComoList> data) {
        this.data = data;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPatientsaddress(String patientsaddress) {
        this.patientsaddress = patientsaddress;
    }

    public void setChc_id(String chc_id) {
        this.chc_id = chc_id;
    }

    public void setPhc_id(String phc_id) {
        this.phc_id = phc_id;
    }
}
