package com.zxq.iov.cloud.sp.vp.dao.journey;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;

/**
 * 安防 行程持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-9 13:31
 * modify date 2015-6-11 17:26
 * @version 0.2, 2015-6-11
 */
public interface IJourneyDaoService extends BaseService<Journey, Long> {

	/**
	 * 创建行程
	 * @param journey		行程对象
	 * @return				行程对象
	 */
	Journey createJourney(Journey journey);

	/**
	 * 更新行程
	 * @param journey		行程对象
	 * @return				行程对象
	 */
	Journey updateJourney(Journey journey);

	/**
	 * 删除行程
	 * @param journeyId  	行程ID
	 */
	void removeJourney(Long journeyId);

	/**
	 * 根据主键得到行程对象
	 * @param journeyId 	行程主键
	 * @return				行程对象
	 */
	Journey findJourneyById(Long journeyId);

	/**
	 * 根据TBOX行程ID得到行程对象
	 * @param tboxJourneyId TBOX行程ID
	 * @param tboxId 		TBOX ID
	 * @return         		行程对象
	 */
	Journey findJourneyByTboxJourneyIdAndTboxId(Integer tboxJourneyId, Long tboxId);
	
}