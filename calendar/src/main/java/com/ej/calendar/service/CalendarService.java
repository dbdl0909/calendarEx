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
	
	//클릭한 날짜에 해당하는 scheduleList를 가져오는 메서드
	public List<Schedule> getScheduleListByDate(String scheduleDate) {
		List<Schedule> scheduleList = scheduleDao.selectScheduleListByDate(scheduleDate);
		scheduleList.addAll(scheduleDao.selectRepeatScheduleListByDate(scheduleDate.substring(5)));
									//날짜가 전부 들어가지 않았기 때문에 날짜의 문자열 5번째부터 자른다.
		return scheduleList;
	}
	
	public int addSchedule(Schedule schedule) {
		if(schedule.getRepeat() == null) {		//repeat이 아닐때 Default 리턴값
			return scheduleDao.insertSchedule(schedule);
		} else {								//schedule.getRepeat().equals("repeat")일 때
			return scheduleDao.insertRepeatSchedules(schedule);
		}
	}
	
	//index.jsp에서 보여줄 달력 화면(넘어오는 매개변수의 값에 따라 바뀐다.)
	public Map<String, Object> getOneDayList(int ddayYear, int ddayMonth, String ddayOption) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//dday -> ?년 ?월 1일
		Calendar dday = Calendar.getInstance();			//오늘 날짜
		dday.set(Calendar.DATE, 1);
		
		if(ddayOption.equals("prev")) {
			dday.set(ddayYear, ddayMonth, 1);
			dday.add(Calendar.MONTH, -1);
		} else if(ddayOption.equals("next")) {
			dday.set(ddayYear, ddayMonth, 1);
			dday.add(Calendar.MONTH, 1);
		}
		logger.info("dday : {}", dday);
		
		//today의 값들과 DB에 있는 값들을 조합해서 OneDay의 List 만들기.
		List<OneDay> oneDayList = new ArrayList<OneDay>();
		
		//1일의 요일 -> 앞의 빈칸 갯수를 구하기 위해
		int firstWeek = dday.get(Calendar.DAY_OF_WEEK);
		logger.info("firstWeek : {}" , firstWeek);
		
		//달의 마지막 날짜를 구하는 메서드
		//int endDay = today.getActualMaximum(Calendar.DAY_OF_MONTH);
		int endDay = dday.getActualMaximum(Calendar.DATE);
		logger.info("이번 달의 마지막 날짜 : {}", endDay);

		//필요한 List size는 날짜가 들어갈 <td> 갯수
		//(firstWeek -1) -> 공백의 갯수
		int listSize = (firstWeek-1)+endDay;
		if(listSize / 7 != 0) {
			listSize = listSize+(7-(listSize%7));
		}
		logger.info("listSize : {}", listSize);
		
		//지난 달의 마지막 날짜 구하는 메서드
		Calendar preMonth = Calendar.getInstance();
		preMonth.set(Calendar.MONTH, dday.get(Calendar.MONTH)-1);
		int preMonthLastDay = preMonth.getActualMaximum(Calendar.DATE);
		int beginSpace = preMonthLastDay-(firstWeek-2);
		logger.info("지난달의 마지막 날짜 preMonthLastDay : {}", preMonthLastDay);
		logger.info("공백의 시작 날짜 beginSpace : {}", beginSpace);
		
		int endSpace = 1;
		for(int i=0; i<listSize; i++) {
			OneDay oneDay;
			if(i<(firstWeek-1)) {				//1일 전에 나오는 공백
				oneDay = new OneDay();
				oneDay.setDay(beginSpace);
				beginSpace++;
			} else if(i<(firstWeek-1)+endDay) {	//1일부터 달의 마지막 날짜까지
				oneDay = new OneDay();
				oneDay.setYear(dday.get(Calendar.YEAR));
				oneDay.setMonth(dday.get(Calendar.MONTH)+1);
				oneDay.setDay((i+1)-(firstWeek-1));
				
				//클릭한 날짜를 문자열로 만든다.(title List를 구해오기 위해)
				String scheduleDate = oneDay.getYear()+"-"+oneDay.getMonth()+"-"+oneDay.getDay();
				
				//특정 날짜에 해당하는 title 리스트
				//Calendar에 보여줄 해당 날짜에 해당하는 title List를 가져오는 메서드
				List<Schedule> scheduleList = scheduleDao.selectScheduleTitleListByDate(scheduleDate);
				
				//매년 반복에 해당하는 title 리스트 뽑아온 뒤 두 개의 title 리스트을 합친다.
				scheduleList.addAll(scheduleDao.selectRepeatScheduleTitleListByDate(scheduleDate.substring(5)));
				
				oneDay.setScheduleList(scheduleList);
				
			} else {					//마지막 날짜 후부터 35(7*5)까지
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
	
	/* 단위 테스트
	public static void main(String[] arg) {
		new CalendarService().getOneDayList();
	}*/
}
