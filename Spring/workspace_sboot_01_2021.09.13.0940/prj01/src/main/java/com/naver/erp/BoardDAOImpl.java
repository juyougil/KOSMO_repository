package com.naver.erp;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

// [DAO 클래스]인 [BoardDAOImpl 클래스] 선언
	//@Repository 를 붙임으로써 [DAO 클래스]임을 지정하게되고, bean 태그로 자동 등록된다.
@Repository
public class BoardDAOImpl implements BoardDAO { 
	
	
	// 속변 sqlSession 선언하고 [SqlSessionTemplate 각체] 를 생성해 저장.
		// @Autowired 역할 -> 속성변수에 붙은 자료형이 [인테페이스]면 이를 구현한 [클래스]를 객체화하여 저장한다.
		// @Autowired 역할 -> 속성변수에 붙은 자료형이 [클래스]이면 이를 객체화하여 저장한다.
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	//[게시판 글 입력 후 입력 적용 행의 개수]를 리턴하는 메소드 선언
	public int insertBoard(BoardDTO boardDTO) {
		
		// SqlSessionTemplate 객체의 insert 메소드 호출로
		// 게시판 글 입력 SQL 구문을 실행하고 입력 성공 행의 갯수얻기
		int boardRegCnt = sqlSession.insert(
				// 실행할 SQL 구문의 위치를 지정하기
				// 실행할 SQL 구문의 위치 문자열 패턴은 아래와 같다.
				// xml 파일 중에 "mapper태그의 namespace명.mapper태그내부의호출할 SQL 구문소유한 태그id값"
				"com.naver.erp.BoardDAO.insertBoard"
				// 호출할 SQL 구문에서 사용할 데이터 저장하기
				,boardDTO
				
				
				
				
		);
		System.out.println("BoardDAOImpl 성공");
		return boardRegCnt;
	}
	
	
	
	
	// [검색한 게시판 목록] 리턴하는 메소드 선언
	public List<Map<String,String>> getBoardList(BoardSearchDTO boardSearchDTO){
		List<Map<String,String>> boardList = this.sqlSession.selectList(
				"com.naver.erp.BoardDAO.getBoardList"	// 실행할 SQL 구문의 위치 지정
				,boardSearchDTO							// 실행할 SQL 구문에서 사용할 데이터 지정
				
		);
		
		return boardList;
	}
	
	
	// [1개 게시판 글 정보] 리턴하는 메소드 선언
	public BoardDTO getBoard(int b_no) {
		// [SqlSessionTemplate 객체]의  selectOne(~,~) 를 호출하여 [1개 게시판 글 정보] 얻기
		BoardDTO boardDTO = this.sqlSession.selectOne(
				//-----------------------------------------------
				// 실행할 SQL 구문의 위치를 지정하기.
				// 실행할 SQL 구문의 위치 문자열 패턴은 아래와 같다.
				// xml 파일 중에 "mapper태그의namespace명.mapper태그내부의호출할SQL구문소유한태그id값"
				//-----------------------------------------------
				"com.naver.erp.BoardDAO.getBoard"	// 실행할 SQL 구문의 위치 지정
				//-----------------------------------------------
				// 실행할 SQL구문에서 사용할 데이터 지정하기
				//-----------------------------------------------
				,b_no								// 실행할 SQL 구문에서 사용할 데이터 지정

				
		);
		// [1개 게시판 글 정보] 리턴하기
		return boardDTO;
	}
	
	
	//[게시판 글 조회수 증가하고 증가하고 수정행의 개수] 리턴하는 메소드 선언
	public int updateReadcount(int b_no) {
		// [SqlSessionTemplate 객체]의  update(~,~) 를 호출하여 [조회수 증가] 하기
		 int updateCnt =this.sqlSession.update(
				 "com.naver.erp.BoardDAO.updateReadcount" // 실행할 SQL 구문의 위치 지정
				 ,b_no										// 실행할 SQL 구문에서 사용할 데이터 지정
		
				 
				 );
		return updateCnt;
	}




	// 삭제/수정할 게시판의 존재 개수를 리턴하는 메소드 선언
	public int getBoardCnt(BoardDTO boardDTO) {
		// [SqlSessionTemplate 객체]의 selectOne(~,~) 를 호출하여 [게시판의 존재 개수] 얻기
		int boardCnt = this.sqlSession.selectOne(
				"com.naver.erp.BoardDAO.getBoardCnt"	// 실행할 SQL 구문의 위치 지정
				,boardDTO								// 실행할 SQL 구문에서 사용할 데이터
		);
		return boardCnt;
	}




	// 삭제/수정할 게시판의 비밀번호 존재 개수를 리턴하는 메소드 선언
	public int getPwdCnt(BoardDTO boardDTO) {
		// [SqlSessionTemplate 객체]의 selectOne(~,~) 를 호출하여 [게시판의 존재 개수] 얻기
		int pwdCnt = this.sqlSession.selectOne(
				"com.naver.erp.BoardDAO.getPwdCnt"	// 실행할 SQL 구문의 위치 지정
				,boardDTO								// 실행할 SQL 구문에서 사용할 데이터
		);
		return pwdCnt;
	}




	// 게시판 수정 후 수정 적용행의 개수를 리턴하는 메소드 선언
	public int updateBoard(BoardDTO boardDTO) {
		//-------------------------------------------------------
		// [SqlSessionTemplate 객체]의 update(~,~) 를 호출하여 [게시판 수정]하기
		//-------------------------------------------------------
		int updateCnt = this.sqlSession.update(
				"com.naver.erp.BoardDAO.updateBoard" // 실행할 SQL 구문의 위치 지정
				,boardDTO							     // 실행할 SQL 구문에서 사용할 데이터 지정
		);
		return updateCnt;

	}



	// 삭제할 게시판의 자식글 존재 개수를 리턴하는 메소드 선언
	public int getChildrenCnt(BoardDTO boardDTO) {
		//-------------------------------------------------------
		// [SqlSessionTemplate 객체]의 selectOne(~,~) 를 호출하여 [게시판 존재 개수] 얻기
		//-------------------------------------------------------
		int childrenCnt = this.sqlSession.selectOne(
				"com.naver.erp.BoardDAO.getChildrenCnt" // 실행할 SQL 구문의 위치 지정
				,boardDTO								// 실행할 SQL 구문에서 사용할 데이터 지정
		);
		return childrenCnt;
	}



	// 삭제할 게시판의 이후 글의 출력 순서번호를 1씩 감소 시킨 후 수정 적용행의 개수를 리턴하는 메소드 선언
	public int downPrintNo(BoardDTO boardDTO) {
		//-------------------------------------------------------
		// [SqlSessionTemplate 객체]의 update(~,~) 를 호출하여 
		// [삭제될 게시판 이후 글의 출력 순서번호를 1씩 감소] 하고 감소된 행의 갯수 얻기
		//-------------------------------------------------------
		int downPrintNoCnt = this.sqlSession.update(
				"com.naver.erp.BoardDAO.downPrintNo" // 실행할 SQL 구문의 위치 지정
				,boardDTO								// 실행할 SQL 구문에서 사용할 데이터 지정
		);
		return downPrintNoCnt;
	}



	// 게시판 삭제 명령한 후 삭제 적용행의 개수를 리턴하는 메소드 선언
	public int deleteBoard(BoardDTO boardDTO) {
		//-------------------------------------------------------
		// [SqlSessionTemplate 객체]의 delete(~,~) 를 호출하여
		// [게시판 삭제 명령]한 후 삭제 적용행의 개수얻기
		//-------------------------------------------------------
		int deleteBoardCnt = this.sqlSession.delete(
				"com.naver.erp.BoardDAO.deleteBoard" // 실행할 SQL 구문의 위치 지정
				,boardDTO								// 실행할 SQL 구문에서 사용할 데이터 지정
		);
		return deleteBoardCnt;
	}



	// [게시판 글 출력번호 1 증가하고 수정 행의 개수] 리턴하는 메소드 선언
	public int updatePrintNo(BoardDTO boardDTO) {
		//-------------------------------------------------------
		// [SqlSessionTemplate 객체]의 update(~,~) 를 호출하여 
		// [게시판 글 출력번호 1 증가하고 수정 행의 개수] 얻기
		//-------------------------------------------------------
		int updatePrintNoCnt = this.sqlSession.update(
				"com.naver.erp.BoardDAO.updatePrintNo" // 실행할 SQL 구문의 위치 지정
				,boardDTO								// 실행할 SQL 구문에서 사용할 데이터 지정
		);
		return updatePrintNoCnt;
	}




	// [검색한 게시판 목록 총개수] 리턴하는 메소드 선언
	public int getBoardListAllCnt(BoardSearchDTO boardSearchDTO) {
		// [SqlSessionTemplate 객체]의 selectOne(~,~) 를 호출하여 [게시판의 존재 개수] 얻기
		int boardListAllCnt = this.sqlSession.selectOne(
				"com.naver.erp.BoardDAO.getBoardListAllCnt"	// 실행할 SQL 구문의 위치 지정
				,boardSearchDTO								// 실행할 SQL 구문에서 사용할 데이터
		);
		return boardListAllCnt;
	}



	
	// 게시판 이미지 이름 가져오는 메소드 선언
	public String getPic(BoardDTO boardDTO) {
		// [SqlSessionTemplate 객체]의 selectOne(~,~) 를 호출하여 [게시판의 존재 개수] 얻기
		String pic = this.sqlSession.selectOne(
				"com.naver.erp.BoardDAO.getPic"	// 실행할 SQL 구문의 위치 지정
				,boardDTO								// 실행할 SQL 구문에서 사용할 데이터
		);
		return pic;
	}

}
