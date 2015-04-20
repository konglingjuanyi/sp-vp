/**
 * Copyright (c) 2007-2011 SAIC. All Rights Reserved.
 * This software is published under the terms of the SAIC IS Dept.
 * 
 * @Project: 
 * @Title: Vehicle.java
 * @Package com.saicmotor.telematics.tsgp.tcmp.vehicle.entity
 * @Description: 
 *
 * @CreateDate : 2012-2-2
 * @CreateBy   : Administrator
 */
package com.zxq.iov.cloud.sp.vp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

/**
 * The Class Vehicle.
 *
 * @ClassName: Vehicle
 * @Description: ()
 * @author Administrator
 * @date 2012-2-2 9:40:07
 * date           modify by          workitem
 * 2012-2-2        Administrator
 * @version 0.1, 2012-2-2
 */
@Entity()
@Table(name = "TB_VEHICLE")
public class Vehicle extends MyBaseEntity<Long> implements Serializable{

    private static final String SEQ_NAME = "seq_tb_vehicle";

	@Id
	@Column(name = "ID", nullable = false, updatable = false,length=14)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
	private	Long id;

	@Column(name = "USER_ID", nullable = true,length=14)	
	private	Long	userId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}