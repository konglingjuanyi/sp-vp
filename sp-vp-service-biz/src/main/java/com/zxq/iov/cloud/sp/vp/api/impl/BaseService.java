package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;

/**
 * 安防 基础服务类
 *
 * @author 叶荣杰
 * create date 2015-7-24 9:57
 * modify date
 * @version 0.1, 2015-7-24
 */
public class BaseService {

    protected void AssertRequired(String argNames, Object... args) throws ServLayerException {
        boolean isNull = false;
        String[] _argNames = argNames.split(",");
        StringBuffer argName = new StringBuffer("");
        int index = 0;
        for(Object arg : args) {
            if(null == arg) {
                argName.append(_argNames[index]);
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
