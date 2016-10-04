package com.ej.calendar.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ej.calendar.service.CalendarService;
import com.ej.calendar.service.Schedule;

@Controller
public class CalendarController {
	private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);
	
	@Autowired
	CalendarService calendarService;
	
	@RequestMapping(value="/calendar/scheduleAdd", method=RequestMethod.POST)
	public String addSchedule(Schedule schedule) {
		String scheduleDday = schedule.getOriginScheduleDate();
		logger.info("scheduleDday : {}", scheduleDday);
		logger.info("반복? : {}", schedule.getRepeat());
		
		calendarService.addSchedule(schedule);
		return "redirect:/calendar/scheduleList?scheduleDday="+scheduleDday;
	}
	
	@RequestMapping(value="/calendar/scheduleList")
	public String scheduleList(Model model, @RequestParam(value="scheduleDday") String scheduleDday) {
		logger.info("클릭한 날짜 : {}", scheduleDday);
		model.addAttribute("scheduleDday", scheduleDday);
		
		//ScheduleList를 받아와서 넘긴다.
		model.addAttribute("scheduleList", calendarService.getScheduleListByDate(scheduleDday));
		
		return "/calendar/scheduleList";
	}
	
	//오늘 날짜에 대한 정보를 가져오는 메서드
	@RequestMapping(value={"/", "/calendar", "/calendar/index"})
	public String calendarIndex(Model model, @RequestParam(value="ddayYear", defaultValue="0") int ddayYear,
											 @RequestParam(value="ddayMonth", defaultValue="0") int ddayMonth,
											 @RequestParam(value="ddayOption", defaultValue="default") String ddayOption) {
		Map<String, Object> map = calendarService.getOneDayList(ddayYear, ddayMonth, ddayOption);
		model.addAttribute("oneDayList", map.get("oneDayList"));
		model.addAttribute("ddayYear", map.get("ddayYear"));
		model.addAttribute("ddayMonth", map.get("ddayMonth"));
		
		return "/calendar/index";
	}
}
