package org.spring.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.springboot.dao.PolicyDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class CityRestController {


    
    @Autowired
    private PolicyDao policyDao;


    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    public List<Map<String,Object>> getTestString() {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("id", "FTP_Huawu_SPEECH");
    	return policyDao.findById(map);
    }
    
    @RequestMapping(value = "/api/map", method = RequestMethod.GET)
    public HashMap<String, Object> getTestMap() {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("a", "A");
    	map.put("b", "b");
    	return map;
    }
    
    @RequestMapping(value = "/api/list", method = RequestMethod.GET)
    public List<Map<String,Object>> TestList(){
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	Map map;
    	 map = new HashMap<String, Object>();
     	map.put("a", "A");
     	map.put("b", "B");
     	list.add(map);
     	 map = new HashMap<String, Object>();
      	map.put("a", "A");
      	map.put("b", "B");
      	list.add(map);
      	return list;
    }
}
