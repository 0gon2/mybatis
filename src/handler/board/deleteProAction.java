package handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;
import controller.CommandHandler;

public class deleteProAction implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String boardid = req.getParameter("boardid");
		if (boardid == null)
			boardid = "1";
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		BoardDataBean article=new BoardDataBean();
	      if(req.getParameter("num")!=null && !req.getParameter("num").equals("")) {
	      article.setNum(Integer.parseInt(req.getParameter("num")));
	      article.setRef(Integer.parseInt(req.getParameter("ref")));
	      article.setRe_step(Integer.parseInt(req.getParameter("re_step")));
	      article.setRe_level(Integer.parseInt(req.getParameter("re_level")));
	      }
	      
	      article.setWriter(req.getParameter("writer"));
	      article.setEmail(req.getParameter("email"));
	      article.setSubject(req.getParameter("subject"));
	      article.setPasswd(req.getParameter("passwd"));
	      article.setContent(req.getParameter("content"));
	      article.setIp(req.getRemoteAddr());
	      
		int num = Integer.parseInt(req.getParameter("num"));
		String passwd = req.getParameter("passwd");
		
		BoardDBBean dbPro = BoardDBBean.getInstance();
		article.setIp(req.getRemoteAddr());
		int deleteCount = dbPro.deleteArticle(num,passwd,boardid);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("deleteCount", deleteCount);
		return "/view/deletePro.jsp";
	}

}
