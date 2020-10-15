package com.woniu.soft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public class MedAdvice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uId;

    private Integer wId;
    
    @TableField(exist = false)
    private List<Adviceinfo> adviceinfo;
    

	public List<Adviceinfo> getAdviceinfo() {
		return adviceinfo;
	}

	public void setAdviceinfo(List<Adviceinfo> adviceinfo) {
		this.adviceinfo = adviceinfo;
	}

	/**
     * 0未完成,1已完成,2已结算
     */
    private Integer status;

    private LocalDateTime creatTime;

    private Double pTotal;

    private Double dTotal;


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

    public Double getpTotal() {
        return pTotal;
    }

    public void setpTotal(Double pTotal) {
        this.pTotal = pTotal;
    }

    public Double getdTotal() {
        return dTotal;
    }

    public void setdTotal(Double dTotal) {
        this.dTotal = dTotal;
    }

	@Override
	public String toString() {
		return "MedAdvice [id=" + id + ", uId=" + uId + ", wId=" + wId + ", adviceinfo=" + adviceinfo + ", status="
				+ status + ", creatTime=" + creatTime + ", pTotal=" + pTotal + ", dTotal=" + dTotal + "]";
	}

   
}
