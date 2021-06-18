package com.ict.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class DAO {
	private static SqlSession ss;
	private synchronized static SqlSession getSession() {
		if(ss == null) {
			ss = DBService.getFactory().openSession(false);
		}
		return ss;
	}
	
	// 전체 게시물의 수
	public static int getCount() {
		int result = 0 ;
		result = getSession().selectOne("count");
		return result;
	}
	
	public static List<VO> getList(int begin, int end){
		List<VO> list = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		list = getSession().selectList("list", map);
		return list;
	}

	public static int getInsert(VO vo) {
		int result = 0 ;
		result = getSession().insert("insert", vo);
		ss.commit();
		return result;
	}

	public static int getHitUp(String idx) {
		int result = 0;
		result = getSession().update("hit_up", idx);
		ss.commit();
		return result;
	}

	public static VO getOneList(String idx) {
		VO vo = null;
		vo = getSession().selectOne("onelist", idx);
		return vo;
	}

	public static int getDelete(VO vo) {
		int result = 0;
		result = getSession().delete("delete", vo);
		ss.commit();		
		return result;
	}
	//댓글 삽입 전에 lev를 업데이트 하자
	public static int getUp_lev(Map map) {
		int result = 0 ;
		result = getSession().update("levup", map);
		ss.commit();
		return result;
	}

	//댓글 삽입
	public static int getAnsInsert(VO ins_vo) {
		System.out.println("DAO까진 왔어");
		int result = 0;
		result = getSession().insert("ans_ins", ins_vo);
		ss.commit();
		
		return result;
	}

	public static int getUpdate(VO vo2) {
		int result = 0;
		result = getSession().update("update", vo2);		
		ss.commit();
		return result;
	}
}