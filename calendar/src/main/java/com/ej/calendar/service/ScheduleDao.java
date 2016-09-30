package com.ej.calendar.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDao {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleDao.class);
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	String NS = "com.ej.calendar.service.ScheduleMapper";
	
	public List<Schedule> selectScheduleTitleListByDate(String scheduleDate) {
		return sqlSession.selectList(NS+".selectScheduleTitleListByDate", scheduleDate);
	}
	
	public List<Schedule> selectScheduleListByDate(String scheduleDate) {
		return sqlSession.selectList(NS+".selectScheduleListByDate", scheduleDate);
	}
	
	public int insertSchedule(Schedule schedule) {
		return sqlSession.insert(NS+".insertSchedule", schedule);
	}
}
