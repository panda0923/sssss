package com.web.action.spot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dao.SpotDao;
import com.web.vo.SpotDTO;
import com.web.web.action.Action;

public class EntInsertAction implements Action {

	
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Long entno = Long.parseLong(request.getParameter("entno"));
		String entname = request.getParameter("entname");
		String entspot = request.getParameter("entspot");
		String enttel = request.getParameter("enttel");
		String entempname = request.getParameter("entempname");
		
		SpotDTO dto = new SpotDTO();
		dto.setEntno(entno);
		dto.setEntname(entname);
		dto.setEntspot(entspot);
		dto.setEnttel(enttel);
		dto.setEntempname(entempname);
		
		SpotDao dao = new SpotDao();
		dao.insert(dto);
		response.sendRedirect("");
	}

}
