package com.zxq.iov.cloud.sp.vp.api;


/**
 * Created with IntelliJ IDEA.
 * User: tokxp
 * Date: 14-9-19
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */
public interface IVehicleService {

    /************* ITbVehicleService ********************/
    /**
     * 更新车辆名称或车牌号
     * @param vin
     * @param vehicleName
     * @param licenceNumber
     */
    void updateVehicle(String vin, String vehicleName, String licenceNumber);

}
