package com.baizhi.mapper;

import com.baizhi.entity.Echarts;

import java.util.List;

public interface EchartMapper {

    List<Echarts> quertLineEcharts();

    List<Echarts> queryProvinceCount();
}
