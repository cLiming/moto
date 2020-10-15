package com.woniu.soft.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 		医嘱主表
 * </p>
 *
 * @author liming
 * @since 2020-10-12
 */
public class MedAdvice implements Serializable {
	@TableField(exist = false)
	private List<Adviceinfo> adviceinfo;
	
	@TableField(exist = false)
	private Prescription prescription;
	
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uId;
    
    private Integer wId;
    
    /**
     * 0未完成，1已完成
     */
    private Integer status;

    private LocalDateTime creatTime;

    private double pTotal;
    
    private double dTotal;

	@Override
	public String toString() {
		return "MedAdvice [adviceinfo=" + adviceinfo + ", prescription=" + prescription + ", id=" + id + ", uId=" + uId + ", wId="
				+ wId + ", status=" + status + ", creatTime=" + creatTime + ", pTotal=" + pTotal + ", dTotal=" + dTotal
				+ "]";
	}


	public double getpTotal() {
		return pTotal;
	}


	public void setpTotal(double pTotal) {
		this.pTotal = pTotal;
	}



	public double getdTotal() {
		return dTotal;
	}



	public void setdTotal(double dTotal) {
		this.dTotal = dTotal;
	}



	public Prescription getPrescription() {
		return prescription;
	}



	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	


	public List<Adviceinfo> getAdviceinfo() {
		return adviceinfo;
	}


	public void setAdviceinfo(List<Adviceinfo> adviceinfo) {
		this.adviceinfo = adviceinfo;
	}


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

    public Integer getwId() {
        return wId;
    }

    public void setwId(Integer wId) {
        this.wId = wId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
    }

}
