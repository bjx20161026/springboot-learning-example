package org.spring.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PolicyDao {
	
	List<Map<String,Object>> findById(@Param("map") Map maps);

}
