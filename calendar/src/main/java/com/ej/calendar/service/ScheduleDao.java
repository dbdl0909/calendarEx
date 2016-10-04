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
	
	//repeat_schedule 테이블의 데이터 삭제 쿼리
	public int deleteRepeatSchedule(int scheduleNo) {
		return sqlSession.delete(NS+".deleteRepeatSchedule", scheduleNo);
	}
	
	//schedule 테이블의 데이터 삭제 쿼리
	public int deleteSchedule(int scheduleNo) {
		return sqlSession.delete(NS+".deleteSchedule", scheduleNo);
	}
	
	//매년 반복으로 저장했을때 title리스트 뽑을때 사용하는 쿼리
	public List<Schedule> selectRepeatScheduleTitleListByDate(String scheduleDate) {
		return sqlSession.selectList(NS+".selectRepeatScheduleTitleListByDate", scheduleDate);
	}
	
	//매년 반복으로 저장했을때 전체 리스트 뽑을때 사용하는 쿼리
	public List<Schedule> selectRepeatScheduleListByDate(String scheduleDate) {
		return sqlSession.selectList(NS+".selectRepeatScheduleListByDate", scheduleDate);
	}
	
	//일정 반복일때(repeat O) 사용하는 쿼리
	public int insertRepeatSchedules(Schedule schedule) {
		return sqlSession.insert(NS+".insertRepeatSchedule", schedule);
	}
	
	//특정 날짜로만 저장했을때 title리스트 뽑을때 사용하는 쿼리
	public List<Schedule> selectScheduleTitleListByDate(String scheduleDate) {
		return sqlSession.selectList(NS+".selectScheduleTitleListByDate", scheduleDate);
	}
	
	//특정 날짜로만 저장했을때 전체 리스트 뽑을때 사용하는 쿼리
	public List<Schedule> selectScheduleListByDate(String scheduleDate) {
		return sqlSession.selectList(NS+".selectScheduleListByDate", scheduleDate);
	}
	
	//일정 반복이 아닐때(repeat X) 사용하는 쿼리
	public int insertSchedule(Schedule schedule) {
		return sqlSession.insert(NS+".insertSchedule", schedule);
	}
}
