package com.imf.haryanachi.networkModel.paitentReg;

public class PaintentRequest {

    private String cr_no, doctor_id, visit_time,chc_id, phc_id,holdermobile,patient_id;

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public void setHoldermobile(String holdermobile) {
        this.holdermobile = holdermobile;
    }

    public void setChc_id(String chc_id) {
        this.chc_id = chc_id;
    }

    public void setPhc_id(String phc_id) {
        this.phc_id = phc_id;
    }

    public String getCr_no() {
        return cr_no;
    }

    public void setCr_no(String cr_no) {
        this.cr_no = cr_no;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(String visit_time) {
        this.visit_time = visit_time;
    }
}
