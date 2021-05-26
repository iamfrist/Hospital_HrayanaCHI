package com.imf.haryanachi.networkModel.pReg;

import com.google.gson.annotations.SerializedName;
import com.imf.haryanachi.networkModel.fetch.ComoList;

import java.util.List;

public class RegRequest {

    private String familyholder, familylastname, holdermobile, familymember, familyaddress,
            familystate, comorbidities,
            familycity, positivemember, pname, lname, p_age, pgender, pnumber,doctor_id,
            password, distric_id, chc_id, phc_id,icmr_id,paddress,date,dose1_date,dose2_date;

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    @SerializedName("no_como")
    private List<ComoList> no_como;

    public void setIcmr_id(String icmr_id) {
        this.icmr_id = icmr_id;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDose1_date(String dose1_date) {
        this.dose1_date = dose1_date;
    }

    public void setDose2_date(String dose2_date) {
        this.dose2_date = dose2_date;
    }

    public void setComorbidities(String comorbidities) {
        this.comorbidities = comorbidities;
    }

    public void setNo_como(List<ComoList> no_como) {
        this.no_como = no_como;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getFamilyholder() {
        return familyholder;
    }

    public void setFamilyholder(String familyholder) {
        this.familyholder = familyholder;
    }

    public String getFamilylastname() {
        return familylastname;
    }

    public void setFamilylastname(String familylastname) {
        this.familylastname = familylastname;
    }

    public String getHoldermobile() {
        return holdermobile;
    }

    public void setHoldermobile(String holdermobile) {
        this.holdermobile = holdermobile;
    }

    public String getFamilymember() {
        return familymember;
    }

    public void setFamilymember(String familymember) {
        this.familymember = familymember;
    }

    public String getFamilyaddress() {
        return familyaddress;
    }

    public void setFamilyaddress(String familyaddress) {
        this.familyaddress = familyaddress;
    }

    public String getFamilystate() {
        return familystate;
    }

    public void setFamilystate(String familystate) {
        this.familystate = familystate;
    }

    public String getFamilycity() {
        return familycity;
    }

    public void setFamilycity(String familycity) {
        this.familycity = familycity;
    }

    public String getPositivemember() {
        return positivemember;
    }

    public void setPositivemember(String positivemember) {
        this.positivemember = positivemember;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getP_age() {
        return p_age;
    }

    public void setP_age(String p_age) {
        this.p_age = p_age;
    }

    public String getPgender() {
        return pgender;
    }

    public void setPgender(String pgender) {
        this.pgender = pgender;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }


    public void setDistric_id(String distric_id) {
        this.distric_id = distric_id;
    }

    public void setChc_id(String chc_id) {
        this.chc_id = chc_id;
    }

    public void setPhc_id(String phc_id) {
        this.phc_id = phc_id;
    }
}
