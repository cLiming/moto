package com.woniu.soft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public class Consultation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uId;

    private Integer status;

    /**
     * 经治医生id
     */
    private Integer wId;

    private String aim;

    /**
     * 患者情况
     */
    private String patientCondition;

    private String diagnosisTreatment;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getwId() {
        return wId;
    }

    public void setwId(Integer wId) {
        this.wId = wId;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getPatientCondition() {
        return patientCondition;
    }

    public void setPatientCondition(String patientCondition) {
        this.patientCondition = patientCondition;
    }

    public String getDiagnosisTreatment() {
        return diagnosisTreatment;
    }

    public void setDiagnosisTreatment(String diagnosisTreatment) {
        this.diagnosisTreatment = diagnosisTreatment;
    }

    @Override
    public String toString() {
        return "Consultation{" +
        "id=" + id +
        ", uId=" + uId +
        ", status=" + status +
        ", wId=" + wId +
        ", aim=" + aim +
        ", patientCondition=" + patientCondition +
        ", diagnosisTreatment=" + diagnosisTreatment +
        "}";
    }
}
