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
public class Adviceinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医嘱id
     */
    private Integer medAdviceId;

    /**
     * 项目id
     */
    private Integer pId;

    private Integer number;
    
    @TableField(exist = false)
    private Project project;
    
    
    public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedAdviceId() {
        return medAdviceId;
    }

    public void setMedAdviceId(Integer medAdviceId) {
        this.medAdviceId = medAdviceId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

	@Override
	public String toString() {
		return "Adviceinfo [id=" + id + ", medAdviceId=" + medAdviceId + ", pId=" + pId + ", number=" + number
				+ ", project=" + project + "]";
	}

    
}
