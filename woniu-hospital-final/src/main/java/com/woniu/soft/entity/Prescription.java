package com.woniu.soft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医嘱id
     */
    private Integer adId;

    /**
     * 0未完成，1已完成
     */
    private Integer status;
    
    @TableField(exist = false)
    private List<PresDrug> presDrugs;
    @TableField(exist = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PresDrug> getPresDrugs() {

        return presDrugs;
	}

	public void setPresDrugs(List<PresDrug> presDrugs) {
		this.presDrugs = presDrugs;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "Prescription [id=" + id + ", adId=" + adId + ", status=" + status + ", presDrugs=" + presDrugs + "]";
	}

  
}
