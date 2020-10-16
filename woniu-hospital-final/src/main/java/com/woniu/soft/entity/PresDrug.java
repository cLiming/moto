package com.woniu.soft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import org.hibernate.validator.constraints.CodePointLength;

/**
 * <p>
 * 
 * </p>
 *
 * @author liming
 * @since 2020-10-12
 */
public class PresDrug implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer presId;


    private Integer number;

    private Integer drugId;
    
    @TableField(exist = false)
    private String drugName;
    
    @TableField(exist = false)
    private Integer baseNumber;
    @TableField(exist = false)
    private  Drug drug;

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public Integer getBaseNumber() {
		return baseNumber;
	}

	public void setBaseNumber(Integer baseNumber) {
		this.baseNumber = baseNumber;
	}

	public Integer getDrugId() {
		return drugId;
	}

	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPresId() {
        return presId;
    }

    public void setPresId(Integer presId) {
        this.presId = presId;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
