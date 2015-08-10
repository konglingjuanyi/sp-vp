package com.zxq.iov.cloud.sp.vp.dao.config.impl;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxConfigSettingDao;
import com.zxq.iov.cloud.sp.vp.dao.config.repo.ITboxConfigSettingRepository;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防 TBOX个性化配置持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-7-29 11:34
 * modify date
 * @version 0.1, 2015-7-29
 */
@Service
public class TboxConfigSettingDaoImpl extends BaseServiceImpl<ITboxConfigSettingRepository, TboxConfigSetting, Long> implements ITboxConfigSettingDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(TboxConfigSettingDaoImpl.class);

    @Autowired
	public TboxConfigSettingDaoImpl(ITboxConfigSettingRepository repo){
		super(repo);
	}

	@Override
	public TboxConfigSetting createTboxConfigSetting(TboxConfigSetting tboxConfigSetting) {
		if (tboxConfigSetting == null){
			LOGGER.error("tboxConfigSetting cannot be null");
		}
		tboxConfigSetting.setId(null);
		super.save(tboxConfigSetting);
		return tboxConfigSetting;
	}

	@Override
	public TboxConfigSetting updateTboxConfigSetting(TboxConfigSetting tboxConfigSetting) {
		if (tboxConfigSetting == null){
			LOGGER.error("tboxConfigSetting cannot be null");
		}
		super.update(tboxConfigSetting);
		return tboxConfigSetting;
	}

	@Override
	public void removeTboxConfigSetting(Long tboxConfigSettingId) {
		if (tboxConfigSettingId == null){
			LOGGER.error("tboxConfigSettingId cannot be null");
		}
		super.delete(tboxConfigSettingId);
	}

	@Override
	public TboxConfigSetting findTboxConfigSettingById(Long tboxConfigSettingId) {
		if (tboxConfigSettingId == null){
			LOGGER.error("tboxConfigSettingId cannot be null");
		}
		return super.findOne(tboxConfigSettingId);
	}

	@Override
	public PageResult<TboxConfigSetting> listTboxConfigSettingByTboxConfigId(PageResult<TboxConfigSetting> pageResult,
																			 Long tboxConfigId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("tboxConfigId", tboxConfigId);
		return super.getPagedListViaBatis(pageResult, paramMap);
	}

	@Override
	public List<TboxConfigSetting> listTboxConfigSettingByTboxId(Long tboxId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("tboxId", tboxId);
		return super.findListViaBatis(paramMap);
	}
}