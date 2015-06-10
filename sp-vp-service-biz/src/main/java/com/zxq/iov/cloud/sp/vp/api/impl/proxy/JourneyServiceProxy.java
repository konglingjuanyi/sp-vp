package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IJourneyService;
import com.zxq.iov.cloud.sp.vp.api.dto.journey.JourneyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.dao.journey.IJourneyDaoService;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 安防 行程服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-10 9:10
 * @version 0.1, 2015-6-10
 */
@Service
@Qualifier("journeyServiceProxy")
public class JourneyServiceProxy implements IJourneyService {

    @Autowired
    @Qualifier("journeyService")
    private IJourneyService journeyService;
    @Autowired
    private IJourneyDaoService journeyDaoService;
    @Autowired
    private IEvent event;

    private static final Integer END_STATUS = 2;

    @Override
    public void startJourney(JourneyDto journeyDto) {
        // 这里存在tboxJourneyId唯一的特殊情况，当满足时跳过事务
        Journey journey = journeyDaoService.findJourneyByTboxJourneyId(journeyDto.getTboxJourneyId());
        if(null != journey && journey.getStatus().intValue() == END_STATUS.intValue()) {
            journeyService.startJourney(journeyDto);
        }
        else {
            event.start(journeyDto);
            journeyService.startJourney(journeyDto);
            event.end(journeyDto);
        }
    }

    @Override
    public void updateJourney(JourneyDto journeyDto, VehicleInfoDto vehicleInfoDto) {
        // 这里存在tboxJourneyId唯一的特殊情况，当满足时跳过事务
        Journey journey = journeyDaoService.findJourneyByTboxJourneyId(journeyDto.getTboxJourneyId());
        if(null != journey && journey.getStatus().intValue() == END_STATUS.intValue()) {
            journeyService.updateJourney(journeyDto, vehicleInfoDto);
        }
        else {
            event.start(journeyDto);
            journeyService.updateJourney(journeyDto, vehicleInfoDto);
            event.end(journeyDto);
        }
    }

    @Override
    public void endJourney(JourneyDto journeyDto, VehicleInfoDto startVehicleInfoDto, VehicleInfoDto endVehicleInfoDto) {
        // 这里存在tboxJourneyId唯一的特殊情况，当满足时跳过事务
        Journey journey = journeyDaoService.findJourneyByTboxJourneyId(journeyDto.getTboxJourneyId());
        if(null != journey && journey.getStatus().intValue() == END_STATUS.intValue()) {
            journeyService.endJourney(journeyDto, startVehicleInfoDto, endVehicleInfoDto);
        }
        else {
            event.start(journeyDto);
            journeyService.endJourney(journeyDto, startVehicleInfoDto, endVehicleInfoDto);
            event.end(journeyDto);
        }
    }
}
