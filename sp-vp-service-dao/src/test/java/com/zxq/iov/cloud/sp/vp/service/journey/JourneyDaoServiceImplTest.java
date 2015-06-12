package com.zxq.iov.cloud.sp.vp.service.journey;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.journey.IJourneyDaoService;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 行程持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-9 13:53
 * modify date 2015-6-11 17:45
 * @version 0.2, 2015-6-11
 */
@Transactional
public class JourneyDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IJourneyDaoService journeyDaoService;

    @Test
    @Rollback(false)
    public void testCreateJourney(){
        Long ownerId = 1L;
        Integer tboxJourneyId = 1;
        Long tboxId = 1L;
        Journey journey = new Journey();
        journey.setOwnerId(ownerId);
        journey.setTboxJourneyId(tboxJourneyId);
        journey.setTboxId(tboxId);
        journey = journeyDaoService.createJourney(journey);
        Assert.assertNotNull(journey);
    }

    @Test
    @Rollback(false)
    public void testUpdateJourney(){
        Long journeyId = 25L;
        Journey journey = journeyDaoService.findJourneyById(journeyId);
        journey.setVin("001");
        journey = journeyDaoService.updateJourney(journey);
        Assert.assertNotNull(journey);
    }

    @Test
    @Rollback(false)
    public void testRemoveJourney(){
        Long journeyId = 25L;
        journeyDaoService.removeJourney(journeyId);
    }

    @Test
    @Rollback(true)
    public void testFindJourneyByTboxJourneyId(){
        Integer tboxJourneyId = 1;
        Long tboxId = 1L;
        Journey journey = journeyDaoService.findJourneyByTboxJourneyIdAndTboxId(tboxJourneyId, tboxId);
        Assert.assertNotNull(journey);
    }
}
