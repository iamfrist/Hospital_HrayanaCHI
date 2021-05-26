package com.imf.haryanachi.networkModel.vitals;

public class VitalsRequest {

private String visit_id,temperature,pulse,spo2,rate,systolic_bp
        ,diastolic_bp,patients_id,chc_id,phc_id,bed_number,centername,wardname,location;

    public void setLocation(String location) {
        this.location = location;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public void setSpo2(String spo2) {
        this.spo2 = spo2;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setSystolic_bp(String systolic_bp) {
        this.systolic_bp = systolic_bp;
    }

    public void setDiastolic_bp(String diastolic_bp) {
        this.diastolic_bp = diastolic_bp;
    }

    public void setPatients_id(String patients_id) {
        this.patients_id = patients_id;
    }

    public void setChc_id(String chc_id) {
        this.chc_id = chc_id;
    }

    public void setPhc_id(String phc_id) {
        this.phc_id = phc_id;
    }

    public void setBed_number(String bed_number) {
        this.bed_number = bed_number;
    }

    public void setCentername(String centername) {
        this.centername = centername;
    }

    public void setWardname(String wardname) {
        this.wardname = wardname;
    }
}
