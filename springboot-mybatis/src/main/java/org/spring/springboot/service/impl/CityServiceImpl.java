package org.spring.springboot.service.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.sml.mybatis.plugins.PaginationInterceptor;
import org.sml.mybatis.plugins.RstInterceptor;
import org.spring.springboot.dao.CityDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 *
 * Created by bysocket on 07/02/2017.
 */

public class CityServiceImpl implements CityService,InitializingBean {

//    @Autowired
//    private CityDao cityDao;
    
    @Autowired
	private SqlSessionFactory sqlSessionFactory;
	
    
    public void init() {
    	sqlSessionFactory.getConfiguration().addInterceptor(new PaginationInterceptor());
    	sqlSessionFactory.getConfiguration().addInterceptor(new RstInterceptor());
    }

    public City findCityByName(String cityName) {
		return null;
//        return cityDao.findByName(cityName);
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		init();
	}
}
