package handler.board;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;
import controller.CommandHandler;

public class ContentAction implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)  {

		String boardid = req.getParameter("boardid");
		if (boardid == null)
			boardid = "1";
	
		int num = Integer.parseInt(req.getParameter("num"));

		String pageNum = req.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		BoardDBBean dbPro = BoardDBBean.getInstance();
		BoardDataBean article = dbPro.getArticle(num, boardid,"content");
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		req.setAttribute("article", article);
		req.setAttribute("ref", ref);
		req.setAttribute("re_step", re_step);
		req.setAttribute("re_level", re_level);
		}catch(Exception e) {
			e.getMessage();
		}
		req.setAttribute("num", num);
		req.setAttribute("boardid", boardid);
		req.setAttribute("pageNum", pageNum);
		
		
		return "/view/content.jsp";
	}

}
