package com.ej.calendar.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {
	private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);
	
	@Autowired
	ScheduleDao scheduleDao;
	
	//Ŭ���� ��¥�� �ش��ϴ� scheduleList�� �������� �޼���
	public List<Schedule> getScheduleListByDate(String scheduleDate) {
		List<Schedule> scheduleList = scheduleDao.selectScheduleListByDate(scheduleDate);
		scheduleList.addAll(scheduleDao.selectRepeatScheduleListByDate(scheduleDate.substring(5)));
									//��¥�� ���� ���� �ʾұ� ������ ��¥�� ���ڿ� 5��°���� �ڸ���.
		return scheduleList;
	}
	
	public int addSchedule(Schedule schedule) {
		if(schedule.getRepeat() == null) {		//repeat�� �ƴҶ� Default ���ϰ�
			return scheduleDao.insertSchedule(schedule);
		} else {								//schedule.getRepeat().equals("repeat")�� ��
			return scheduleDao.insertRepeatSchedules(schedule);
		}
	}
	
	//index.jsp���� ������ �޷� ȭ��(�Ѿ���� �Ű������� ���� ���� �ٲ��.)
	public Map<String, Object> getOneDayList(int ddayYear, int ddayMonth, String ddayOption) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//dday -> ?�� ?�� 1��
		Calendar dday = Calendar.getInstance();			//���� ��¥
		dday.set(Calendar.DATE, 1);
		
		if(ddayOption.equals("prev")) {
			dday.set(ddayYear, ddayMonth, 1);
			dday.add(Calendar.MONTH, -1);
		} else if(ddayOption.equals("next")) {
			dday.set(ddayYear, ddayMonth, 1);
			dday.add(Calendar.MONTH, 1);
		}
		logger.info("dday : {}", dday);
		
		//today�� ����� DB�� �ִ� ������ �����ؼ� OneDay�� List �����.
		List<OneDay> oneDayList = new ArrayList<OneDay>();
		
		//1���� ���� -> ���� ��ĭ ������ ���ϱ� ����
		int firstWeek = dday.get(Calendar.DAY_OF_WEEK);
		logger.info("firstWeek : {}" , firstWeek);
		
		//���� ������ ��¥�� ���ϴ� �޼���
		//int endDay = today.getActualMaximum(Calendar.DAY_OF_MONTH);
		int endDay = dday.getActualMaximum(Calendar.DATE);
		logger.info("�̹� ���� ������ ��¥ : {}", endDay);

		//�ʿ��� List size�� ��¥�� �� <td> ����
		//(firstWeek -1) -> ������ ����
		int listSize = (firstWeek-1)+endDay;
		if(listSize / 7 != 0) {
			listSize = listSize+(7-(listSize%7));
		}
		logger.info("listSize : {}", listSize);
		
		//���� ���� ������ ��¥ ���ϴ� �޼���
		Calendar preMonth = Calendar.getInstance();
		preMonth.set(Calendar.MONTH, dday.get(Calendar.MONTH)-1);
		int preMonthLastDay = preMonth.getActualMaximum(Calendar.DATE);
		int beginSpace = preMonthLastDay-(firstWeek-2);
		logger.info("�������� ������ ��¥ preMonthLastDay : {}", preMonthLastDay);
		logger.info("������ ���� ��¥ beginSpace : {}", beginSpace);
		
		int endSpace = 1;
		for(int i=0; i<listSize; i++) {
			OneDay oneDay;
			if(i<(firstWeek-1)) {				//1�� ���� ������ ����
				oneDay = new OneDay();
				oneDay.setDay(beginSpace);
				beginSpace++;
			} else if(i<(firstWeek-1)+endDay) {	//1�Ϻ��� ���� ������ ��¥����
				oneDay = new OneDay();
				oneDay.setYear(dday.get(Calendar.YEAR));
				oneDay.setMonth(dday.get(Calendar.MONTH)+1);
				oneDay.setDay((i+1)-(firstWeek-1));
				
				//Ŭ���� ��¥�� ���ڿ��� �����.(title List�� ���ؿ��� ����)
				String scheduleDate = oneDay.getYear()+"-"+oneDay.getMonth()+"-"+oneDay.getDay();
				
				//Ư�� ��¥�� �ش��ϴ� title ����Ʈ
				//Calendar�� ������ �ش� ��¥�� �ش��ϴ� title List�� �������� �޼���
				List<Schedule> scheduleList = scheduleDao.selectScheduleTitleListByDate(scheduleDate);
				
				//�ų� �ݺ��� �ش��ϴ� title ����Ʈ �̾ƿ� �� �� ���� title ����Ʈ�� ��ģ��.
				scheduleList.addAll(scheduleDao.selectRepeatScheduleTitleListByDate(scheduleDate.substring(5)));
				
				oneDay.setScheduleList(scheduleList);
				
			} else {					//������ ��¥ �ĺ��� 35(7*5)����
				oneDay = new OneDay();
				oneDay.setDay(endSpace);
				endSpace++;
			}
			logger.info("scheduleList : {}", oneDay.getScheduleList());
			
			//logger.info("oneDay : {}", oneDay);
			oneDayList.add(oneDay);
		}
		map.put("oneDayList", oneDayList);
		map.put("ddayYear", dday.get(Calendar.YEAR));
		map.put("ddayMonth", dday.get(Calendar.MONTH));
		
		return map;
	}
	
	/* ���� �׽�Ʈ
	public static void main(String[] arg) {
		new CalendarService().getOneDayList();
	}*/
}
