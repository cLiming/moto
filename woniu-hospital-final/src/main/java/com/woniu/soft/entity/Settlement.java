package com.woniu.soft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
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
public class Settlement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医嘱id
     */
    private Integer uid;

    /**
     * 结算时间
     */
    private LocalDateTime flashTime;

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

    public LocalDateTime getFlashTime() {
        return flashTime;
    }

    public void setFlashTime(LocalDateTime flashTime) {
        this.flashTime = flashTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Settlement{" +
                "id=" + id +
                ", uid=" + uid +
                ", flashTime=" + flashTime +
                ", status=" + status +
                '}';
    }
}
