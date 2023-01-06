package com.tjoeun.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tjoeun.vo.ApprovalVO;
import com.tjoeun.vo.BoardVO;
import com.tjoeun.vo.EmpVO;
import com.tjoeun.vo.MailBoxesVO;
import com.tjoeun.vo.MeetRoomVO;
import com.tjoeun.vo.Param;
import com.tjoeun.vo.ReportVO;
import com.tjoeun.vo.TodoVO;

// mapper 연결에 사용하는 인터페이스
public interface MyBatisDAO {

	// emp
	int confirmEmpno(int empno);
	EmpVO selectlogin(int empno);
	EmpVO registerCheck(String empno);	
	void register(EmpVO vo);
	void registerUpdate(EmpVO vo);
	
	// board
	int selectCount(String category);
	ArrayList<BoardVO> selectList(Param param);
	int selecCountMulti(Param param);
	ArrayList<BoardVO> selectListMulti(Param param);
	int selectQNACount(String category);
	ArrayList<BoardVO> selectQNAList(Param param);
	int countByDept(int deptno);
	ArrayList<EmpVO> selectByDept(HashMap<String, Integer> hmap);
	int mywrite_selectCount(int empno);
	ArrayList<BoardVO> mywrite_selectList(HashMap<String, Integer> myhmap);
	void boardinsert(BoardVO vo);
	void answerinsert(BoardVO vo);
	void increment_hit(int idx);
	BoardVO selectContentByIdx(int idx);
	int selectCommentCount(int idx);
	ArrayList<BoardVO> selectCommentList(HashMap<String, Integer> hmap);
	void contentdelete(BoardVO vo);
	void boardupdate(BoardVO vo);
	void commentinsert(BoardVO vo);
	void commentdelete(BoardVO vo);
	ArrayList<BoardVO> selectNoticeList(String string);
	
	// report
	int selectReportCount(MyBatisDAO mapper);
	ArrayList<ReportVO> seletReportList(HashMap<String, Integer> hmap);
	ReportVO selectReportByIdx(int idx);
	void deleteReport(ReportVO vo);
	void updateReport(ReportVO vo);
	
	// mailboxes
	int receiveMailSelectCount(String email);
	ArrayList<MailBoxesVO> receiveMailSelectList(Param param);
	int sendMailSelectCount(String email);
	ArrayList<MailBoxesVO> sendMailSelectList(Param param);
	int trashMailSelectCount(String email);
	ArrayList<MailBoxesVO> trashMailSelectList(Param param);
	MailBoxesVO mailSelectByIdx(int idx);
	void mailTrash(MailBoxesVO mo);
	void mailRestore(MailBoxesVO mo);
	void mailInsert(MailBoxesVO mo);
	void mailDelete(MailBoxesVO mo);
	
	// todo
	int todocount(TodoVO vo);
	void todoinsert(TodoVO vo);
	ArrayList<TodoVO> todolist(TodoVO todovo);
	void todoupdateS(TodoVO vo);
	ArrayList<TodoVO> caltodolist(String setdate);
	
	//meetroom
	void meetroominsert(MeetRoomVO mvo);
	ArrayList<MeetRoomVO> list103(MeetRoomVO mvo);
	ArrayList<MeetRoomVO> list222(MeetRoomVO mvo);
	ArrayList<MeetRoomVO> list503(MeetRoomVO mvo);
	ArrayList<MeetRoomVO> list710(MeetRoomVO mvo);
	ArrayList<MeetRoomVO> list901(MeetRoomVO mvo);
	ArrayList<MeetRoomVO> list907(MeetRoomVO mvo);
	
	//manager
	int countwaiting(); // emp.xml
	ArrayList<EmpVO> selectwaiting(HashMap<String, Integer> hmap); // emp.xml
	void account_approval(EmpVO vo); // emp.xml
	
	// approval
	int approvalCount_YET(int empno);
	int approvalCount_UNDER(int empno);
	int approvalCount_DONE(int empno);
	List<ApprovalVO> getRecentList(int empno);
	List<ApprovalVO> getRecentList1(int empno);
	List<ApprovalVO> getRecentList2(int empno);
	int getListCount(String searchText);
	List<EmpVO> selectMemberAllForApproval(int empno);
	int saveLetterOfApproval(ApprovalVO vo);
	int saveLetterOfApproval2(ApprovalVO vo);
	int saveLetterOfApproval3(ApprovalVO vo);
	ApprovalVO findListByNo(int appNo);
	List<EmpVO> selectSearchedMemberForApproval(String searchData, int empno);
	int rejectUpdate(ApprovalVO vo);
	List<EmpVO> getSearchMember(String userName);
	int approved1(int appNo);
	int approved2(int appNo);
	int approved3(int appNo);
	int insertApproval(ApprovalVO vo);
	int insertLeave(ApprovalVO vo);
	int insertReceive(ApprovalVO vo);
	int saveExpenseReport(ApprovalVO vo);
	int saveExpenseReport2(ApprovalVO vo);
	int saveExpenseReport3(ApprovalVO vo);
	ApprovalVO findExpenseReportListByNo(int appNo);
	ApprovalVO findListByLeaveNo(int appNo);

	
	
}