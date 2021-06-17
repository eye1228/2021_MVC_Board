package com.ict.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class ListCommand implements Command {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		Paging pvo = new Paging();
		// 1. ��ü �Խù��� ��
		int su = DAO.getCount();
		pvo.setTotalRecord(su);

		// 2. ��ü �Խù��� ���� ������ ��ü �������� ���� ���Ѵ�.
		pvo.setTotalPage(pvo.getTotalRecord() / pvo.getNumPerPage());

		// ����) ���� �������� �����ϸ� ��ü ���������� �� ������ �߰�
		if (pvo.getTotalRecord() % pvo.getNumPerPage() != 0) {
			pvo.setTotalPage(pvo.getTotalPage() + 1);
		}
		// 3. ���� ������ ���ϱ�
		String cPage = request.getParameter("cPage");
		if (cPage == null)
			cPage = "1";
		pvo.setNowPage(Integer.parseInt(cPage));

		// 4. ���۹�ȣ, ����ȣ ���ϱ�
		pvo.setBegin((pvo.getNowPage() - 1) * pvo.getNumPerPage() + 1);
		pvo.setEnd((pvo.getBegin() - 1) + pvo.getNumPerPage());

		// DBó��
		List<VO> list = DAO.getList(pvo.getBegin(), pvo.getEnd());

		// 5. ���ۺ��, ����� ���ϱ�(list.jsp���� ����¡���)
		pvo.setBeginBlock((int) (pvo.getNowPage() - 1) / pvo.getPagePerBlock() * pvo.getPagePerBlock() + 1);
		pvo.setEndBlock(pvo.getBeginBlock() + pvo.getPagePerBlock() - 1);

		// ����) endBlock�� totalPage���� Ŭ�� �ִ�.
		if (pvo.getEndBlock() > pvo.getTotalPage()) {
			pvo.setEndBlock(pvo.getTotalPage());
		}

		// 6. reqeust�� ����
		request.setAttribute("list", list);
		request.setAttribute("pvo", pvo);

		return "view/list.jsp";
	}
}
