package com.woniu.soft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class PresDrug implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer presId;
    
    @TableField(value = "drugId")
    private Integer drId;

    private Integer number;
    
    @TableField(exist = false)
    private Drug drug;
    
    public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
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

    public Integer getDrId() {
		return drId;
	}

	public void setDrId(Integer drId) {
		this.drId = drId;
	}

	public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

	@Override
	public String toString() {
		return "PresDrug [id=" + id + ", presId=" + presId + ", drId=" + drId + ", number=" + number + ", drug=" + drug
				+ "]";
	}

    
}
