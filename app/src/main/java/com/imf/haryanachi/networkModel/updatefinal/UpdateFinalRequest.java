package com.imf.haryanachi.networkModel.updatefinal;

public class UpdateFinalRequest {

    String visit_id,patients_status,patients_id,hospital_id,remark;

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    public void setPatients_status(String patients_status) {
        this.patients_status = patients_status;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public void setPatients_id(String patients_id) {
        this.patients_id = patients_id;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
