package handler.board;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.BoardDBBean;
import board.BoardDataBean;
import board.DBmybatis;
import controller.CommandHandler;

public class WriteProUploadAction implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    //----------업로드------------- 
		String realFolder=""; //웹 어플리케이션상의 절대 경로
		String encType = "utf-8"; //인코딩 타입
		int maxSize= 10*1024*1024; // 최대 업로드될 파일크기 10mb
		ServletContext context=req.getServletContext();
		realFolder=context.getRealPath("fileSave");
		MultipartRequest multi=null;
		multi=new MultipartRequest(req, realFolder,maxSize,encType,new DefaultFileRenamePolicy());
		Enumeration files=multi.getFileNames();
		String filename="";
		File file=null;  
		
		while(files.hasMoreElements()) {
			String name =(String) files.nextElement();
			filename=multi.getFilesystemName(name);
			file=multi.getFile(name);
			 
				/*
				 * String original = multi.getOriginalFileName(name); 
			  	 *	String type=multi.getContentType(name); 
				 * 
				 * */
		}
		//---------------------------
		 String pageNum = multi.getParameter("pageNum");
		 String boardid = multi.getParameter("boardid");
		 if(pageNum==null||pageNum=="") pageNum="1";
		      
		
	      BoardDataBean article=new BoardDataBean();
	      if(multi.getParameter("num")!=null && !multi.getParameter("num").equals("")) {
	      article.setNum(Integer.parseInt(multi.getParameter("num")));
	      article.setRef(Integer.parseInt(multi.getParameter("ref")));
	      article.setRe_step(Integer.parseInt(multi.getParameter("re_step")));
	      article.setRe_level(Integer.parseInt(multi.getParameter("re_level")));
	      }
	      
	      article.setWriter(multi.getParameter("writer"));
	      article.setEmail(multi.getParameter("email"));
	      article.setSubject(multi.getParameter("subject"));
	      article.setPasswd(multi.getParameter("passwd"));
	      article.setContent(multi.getParameter("content"));
	      article.setIp(req.getRemoteAddr());
	      article.setBoardid(boardid);
	      
	      if(file!=null) {
	    	  article.setFilename(filename);
	    	  article.setFilesize((int)file.length());
	      }else {
	    	  article.setFilename("");
	    	  article.setFilesize(0);
	      }
	      
	      System.out.println(article);
	      DBmybatis dbPro = DBmybatis.getInstance();
	      dbPro.insertArticle(article);
		req.setAttribute("pageNum", pageNum);
		
		req.setAttribute("boardid", boardid);
		HttpSession session = req.getSession();
		session.setAttribute("filename", article.getFilename());
		System.out.println(article.getFilename());
		res.sendRedirect(req.getContextPath()+"/board/list?pageNum="+pageNum+"&boardid="+boardid);
		
		return null;
	}

}
