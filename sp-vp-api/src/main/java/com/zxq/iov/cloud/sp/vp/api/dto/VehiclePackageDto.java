package com.zxq.iov.cloud.sp.vp.api.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: tokxp
 * Date: 14-11-6
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
public class VehiclePackageDto implements Serializable {
	private static final long serialVersionUID = 2902043158359933262L;

	private Long packageId;

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

}
