package egovframework.jtLunch.admin.cmmn.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.owner.DTO.DateData;

@Repository("AdminCmmnDAO")
public class AdminCmmnDAO {
	@Inject
	SqlSession sqlSession;

	public int CountAteTerm(DateData dateData) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("CheckAteUser.CountAteTerm",dateData);
	}
	public int CountAteTermTable(String ate_Date){
		// TODO Auto-generated method stub
		return sqlSession.selectOne("CheckAteUser.CountAteTermTable",ate_Date);
	}
}
