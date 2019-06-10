package com.baizhi.serviceImpl;

import com.baizhi.entity.Echarts;
import com.baizhi.mapper.EchartMapper;
import com.baizhi.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EchartsServiceImpl implements EchartsService {
    @Autowired
    EchartMapper echartMapper;

    @Override
    public List<Echarts> findByUserAddEcharts() {
        List<Echarts> echarts = echartMapper.quertLineEcharts();
        return echarts;
    }

    @Override
    public List<Echarts> findByUserProvinceCount() {
        List<Echarts> echarts = echartMapper.queryProvinceCount();
        return echarts;
    }
}
