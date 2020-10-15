package com.woniu.soft.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public class ReturnApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uId;

    private Integer drId;

    private Integer number;

    private Integer wId;

    private Integer status;
    
    @TableField(exist = false)
    private Drug drug;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Workers worker;
    
    public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Workers getWorker() {
		return worker;
	}

	public void setWorker(Workers worker) {
		this.worker = worker;
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

	@Override
	public String toString() {
		return "ReturnApplication [id=" + id + ", uId=" + uId + ", drId=" + drId + ", number=" + number + ", wId=" + wId
				+ ", status=" + status + ", drug=" + drug + ", user=" + user + ", worker=" + worker + "]";
	}
	
}
