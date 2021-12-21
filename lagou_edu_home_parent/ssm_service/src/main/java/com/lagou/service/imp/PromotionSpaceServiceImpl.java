package com.lagou.service.imp;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    /*
        获取所有广告位
     */
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {

        List<PromotionSpace> list = promotionSpaceMapper.findAllPromotionSpace();
        return list;
    }

    /*
        添加广告位
     */
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        // 封装数据
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());

        // 调用mapper方法
        promotionSpaceMapper.savePromotionSpace(promotionSpace);

    }

    /*
        根据id查询广告位信息
     */
    public PromotionSpace findPromotionSpaceById(int id) {

        PromotionSpace promotionSpace = promotionSpaceMapper.findPromotionSpaceById(id);

        return promotionSpace;

    }

    /*
        修改广告位
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace){

        // 封装数据
        promotionSpace.setUpdateTime(new Date());

        // 调用mapper方法
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);

    }

}
