package handler.board;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.DBmybatis;
import controller.CommandHandler;

public class ListAction implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String boardid = req.getParameter("boardid");
		if (boardid == null)
			boardid = "1";
		int pageSize = 5;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List articleList = null;
		DBmybatis dbPro = DBmybatis.getInstance();
		count = dbPro.getArticleCount(boardid);
		if (count > 0) {
			articleList = dbPro.getArticles(startRow, endRow, boardid);
		}
		number = count - (currentPage - 1) * pageSize;
		
		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;
		
		req.setAttribute("boardid",boardid);
		req.setAttribute("pageCount",pageCount);
		req.setAttribute("endPage",endPage);
		req.setAttribute("bottomLine",bottomLine);
		req.setAttribute("startPage",startPage);
		req.setAttribute("currentPage",currentPage);
		req.setAttribute("sdf",sdf);
		req.setAttribute("articleList",articleList);
		req.setAttribute("number",number);
		req.setAttribute("count",count);

		return "/view/list.jsp";
	}

	
	
	
	/*
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception{
	
	}
*/
}
