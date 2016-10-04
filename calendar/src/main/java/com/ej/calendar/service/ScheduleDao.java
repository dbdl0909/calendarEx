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
	
	//repeat_schedule ���̺��� ������ ���� ����
	public int deleteRepeatSchedule(int scheduleNo) {
		return sqlSession.delete(NS+".deleteRepeatSchedule", scheduleNo);
	}
	
	//schedule ���̺��� ������ ���� ����
	public int deleteSchedule(int scheduleNo) {
		return sqlSession.delete(NS+".deleteSchedule", scheduleNo);
	}
	
	//�ų� �ݺ����� ���������� title����Ʈ ������ ����ϴ� ����
	public List<Schedule> selectRepeatScheduleTitleListByDate(String scheduleDate) {
		return sqlSession.selectList(NS+".selectRepeatScheduleTitleListByDate", scheduleDate);
	}
	
	//�ų� �ݺ����� ���������� ��ü ����Ʈ ������ ����ϴ� ����
	public List<Schedule> selectRepeatScheduleListByDate(String scheduleDate) {
		return sqlSession.selectList(NS+".selectRepeatScheduleListByDate", scheduleDate);
	}
	
	//���� �ݺ��϶�(repeat O) ����ϴ� ����
	public int insertRepeatSchedules(Schedule schedule) {
		return sqlSession.insert(NS+".insertRepeatSchedule", schedule);
	}
	
	//Ư�� ��¥�θ� ���������� title����Ʈ ������ ����ϴ� ����
	public List<Schedule> selectScheduleTitleListByDate(String scheduleDate) {
		return sqlSession.selectList(NS+".selectScheduleTitleListByDate", scheduleDate);
	}
	
	//Ư�� ��¥�θ� ���������� ��ü ����Ʈ ������ ����ϴ� ����
	public List<Schedule> selectScheduleListByDate(String scheduleDate) {
		return sqlSession.selectList(NS+".selectScheduleListByDate", scheduleDate);
	}
	
	//���� �ݺ��� �ƴҶ�(repeat X) ����ϴ� ����
	public int insertSchedule(Schedule schedule) {
		return sqlSession.insert(NS+".insertSchedule", schedule);
	}
}
