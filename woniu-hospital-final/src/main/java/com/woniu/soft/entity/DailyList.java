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
public class DailyList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private Integer bedid;

    /**
     * 0为未结算，1为已结算
     */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getBedid() {
        return bedid;
    }

    public void setBedid(Integer bedid) {
        this.bedid = bedid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DailyList{" +
        "id=" + id +
        ", uid=" + uid +
        ", bedid=" + bedid +
        ", status=" + status +
        "}";
    }
}
