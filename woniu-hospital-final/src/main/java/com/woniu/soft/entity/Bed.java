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
public class Bed implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer roomName;

    private String bedName;

    private Integer status;

    /**
     * 床位费
     */
    private Integer price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomName() {
        return roomName;
    }

    public void setRoomName(Integer roomName) {
        this.roomName = roomName;
    }

    public String getBedName() {
        return bedName;
    }

    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Bed{" +
        "id=" + id +
        ", roomName=" + roomName +
        ", bedName=" + bedName +
        ", status=" + status +
        ", price=" + price +
        "}";
    }
}
