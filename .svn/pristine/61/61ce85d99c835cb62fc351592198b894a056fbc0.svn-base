package egovframework.jtLunch.admin.owner.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.jtLunch.admin.owner.DTO.DateData;
import egovframework.jtLunch.main.qrcode.DTO.QrCodeDTO;

@Repository("CheckAteUserDAO")
public class CheckAteUserDAO {
	@Inject
	SqlSession sqlSession;
	
	public int countAteUser(DateData dateData) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("CheckAteUser.countAteUser",dateData);
	}
	
	public List<QrCodeDTO> ateuser_list(DateData dateData){
		// TODO Auto-generated method stub
		return sqlSession.selectList("CheckAteUser.AteUserList",dateData);
	}
	
	public List<QrCodeDTO> DayAteUserAll(String ate_date){
		// TODO Auto-generated method stub
		return sqlSession.selectList("CheckAteUser.DayAteUserAll",ate_date);
	}
}
