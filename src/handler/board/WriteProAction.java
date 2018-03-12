package handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDataBean;
import board.DBmybatis;
import controller.CommandHandler;

public class WriteProAction implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    String pageNum = req.getParameter("pageNum");
	      String boardid = req.getParameter("boardid");
	      if(pageNum==null||pageNum=="") pageNum="1";
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
	      
	      
	      System.out.println(article);
	      DBmybatis dbPro = DBmybatis.getInstance();
	      dbPro.insertArticle(article);
		req.setAttribute("pageNum", pageNum);//이부분 다시보기
		req.setAttribute("boardid", boardid);//이부분 다시보기
		res.sendRedirect(req.getContextPath()+"/board/list?pageNum="+pageNum+"&boardid="+boardid);
		
		return null;
	}

}
