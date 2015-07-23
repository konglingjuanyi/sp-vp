package com.zxq.iov.cloud.sp.vp.dao.svt;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;

/**
 * 安防 被盗警报持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-15 11:56
 * modify date
 * @version 0.1, 2015-6-15
 */
public interface IStolenAlarmDaoService extends BaseService<StolenAlarm, Long> {

	/**
	 * 创建被盗警报
	 * @param stolenAlarm	被盗警报对象
	 * @return				被盗警报对象
	 */
	StolenAlarm createStolenAlarm(StolenAlarm stolenAlarm);

	/**
	 * 更新被盗警报
	 * @param stolenAlarm	被盗警报对象
	 * @return				被盗警报对象
	 */
	StolenAlarm updateStolenAlarm(StolenAlarm stolenAlarm);

	/**
	 * 删除被盗警报
	 * @param stolenAlarmId  被盗警报ID
	 */
	void removeStolenAlarm(Long stolenAlarmId);

	/**
	 * 根据主键得到被盗警报对象
	 * @param stolenAlarmId 被盗警报主键
	 * @return				被盗警报对象
	 */
	StolenAlarm findStolenAlarmById(Long stolenAlarmId);
	
}