package board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.MybatisConnector;

public class DBmybatis extends MybatisConnector{
	  /* private final String namespace="ldg.mybatis";
	   private static DBmybatis instance=new DBmybatis();
	   public static DBmybatis getInstance() {
	      return instance;
	   }
	   SqlSession sqlSession;*/
	   
	   private final String namespace="ldg.mybatis";
	   private static DBmybatis instance = new DBmybatis();
	   public DBmybatis getInstance() {
		   return instance; 
	   }
	   SqlSession sqlSession;
	   
	   /*public int getArticleCount(String boardid) {
			int x = 0;
			sqlSession=sqlSession();
			Map<String, String> map = new HashMap<String, String>();
			map.put("boardid", boardid);
			x=sqlSession.selectOne(namespace+".getArticleCount", map);
			sqlSession.close();
			return x;
		}*/
	   
		/*public List getArticles(int startRow, int endRow, String boardid) {
			sqlSession=sqlSession();
			Map map = new HashMap();
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("boardid", boardid);
			List li=sqlSession.selectList(namespace+".getArticles", map);
			sqlSession.close();
			return li;
		}*/
		
		/*public void insertArticle(BoardDataBean article) {
			sqlSession=sqlSession();
			int number=sqlSession.selectOne(namespace+".getNextNumber");
			if(article.getNum()!=0) {//답글쓰기
				sqlSession.update(namespace+".updateRe_step", article);
				article.setRe_level(article.getRe_level()+1);
				article.setRe_step(article.getRe_step()+1);
			}else {//새글쓰기
				article.setRef(number);
				article.setRe_step(0);
				article.setRe_level(0);
			}
			article.setNum(number);
			sqlSession.insert(namespace+".insertBoard", article);
			sqlSession.commit();
			sqlSession.close();
		}*/
	   public void insertArticle(BoardDataBean article) {
		   sqlSession=sqlSession();
		   int number = sqlSession.selectOne(namespace+".getNextNumber");
		   if(article.getNum()!=0) {
			   sqlSession.update(namespace+".updateRe_step",article);
			   article.setRe_level(article.getRe_level()+1);
			   article.setRe_step(article.getRe_step()+1);
		   }else {
			   article.setRef(number);
			   article.setRe_step(0);
		   }
	   }
		
}
