package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class OneListCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("idx");
		String cPage = request.getParameter("cPage");
		
		//��Ʈ�� ������Ʈ
		int result = DAO.getHitUp(idx);
		
		//�� ����
		VO vo = DAO.getOneList(idx);
		
		//������ ������ ���ؼ� session����
		request.getSession().setAttribute("vo", vo);
		
		//cPage�� ����
		request.setAttribute("cPage", cPage);
		
		
		return "view/onelist.jsp";
	}

}
