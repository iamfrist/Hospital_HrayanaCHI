package com.imf.haryanachi.networkModel.status;

import java.io.Serializable;

public class StatusRequest implements Serializable {

    private String visit_id,patients_status;
    private  String center_name,ward_name,bed_number;
    private String patients_id;

    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }

    public String getBed_number() {
        return bed_number;
    }

    public void setBed_number(String bed_number) {
        this.bed_number = bed_number;
    }


    public void setPatients_id(String patients_id) {
        this.patients_id = patients_id;
    }

    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    public String getPatients_status() {
        return patients_status;
    }

    public void setPatients_status(String patients_status) {
        this.patients_status = patients_status;
    }
}
