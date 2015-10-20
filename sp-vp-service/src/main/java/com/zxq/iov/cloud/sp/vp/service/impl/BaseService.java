/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-24       荣杰         1.0            Initial Version
 * 2015-08-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.impl.BaseService
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.common.constants.ExceptionConstants;

/**
 * 安防服务 基础服务类
 */
public class BaseService {

    /**
     * 验证必填的输入参数
     * @param argNames              参数名称
     * @param args                  参数变量
     * @throws ServLayerException   业务异常
     */
    protected void AssertRequired(String argNames, Object... args) throws ServLayerException {
        boolean isNull = false;
        String[] _argNames = argNames.split(",");
        StringBuffer argName = new StringBuffer("");
        int index = 0;
        for(Object arg : args) {
            if(null == arg) {
                argName.append(_argNames[index] + " ");
                isNull = true;
            }
            index++;
        }
        if(isNull) {
            throw new ServLayerException(ExceptionConstants.PARAMS_IS_NULL,
                    argName.toString() + " are not allowed to be null");
        }
    }

}
