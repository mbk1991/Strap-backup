package com.kh.strap.schedule.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.strap.schedule.domain.Schedule;

public interface ScheduleStore {
	public int insertSchedule(SqlSession session, Schedule schedule);
	public int insertDaySchedule(SqlSession session, Schedule schedule);
	public List<Schedule> selectAllSchedule(SqlSession session, String memberId);
	public int deleteSchedule(SqlSession session, Integer matchNo);
	public int updateSchedule(SqlSession session, Schedule schedule);
	public Schedule endSchedule(SqlSession session, Map<String, String> map);
	public int sendSurvey(SqlSession session, Schedule schedule);
}
