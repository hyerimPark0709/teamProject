package com.tjoeun.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tjoeun.dao.MyBatisDAO;
import com.tjoeun.vo.BoardList;
import com.tjoeun.vo.BoardVO;
import com.tjoeun.vo.EmpVO;
import com.tjoeun.vo.MessageList;
import com.tjoeun.vo.MessageVO;
import com.tjoeun.vo.Param;

@Controller
@RequestMapping("/mypage")
public class mypage {

	private static final Logger logger = LoggerFactory.getLogger(mypage.class);

	AbstractApplicationContext CTX = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
	Param param = CTX.getBean("param", Param.class);

	private int currentPage = 1;

	@Autowired
	private SqlSession sqlSession;

	@RequestMapping("/myinfo_view")
	public String myinfo_view(HttpServletRequest request, Model model) {

		return "mypage/myinfo_view";
	}

	@RequestMapping("/mywrite_view")
	public String mywrite_view(HttpServletRequest request, Model model) {

		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);

		HttpSession session = request.getSession();
		EmpVO vo = (EmpVO) session.getAttribute("EmpVO");

		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {

		}

		int PageSize = 10;

		int totalCount = mapper.mywrite_selectCount(vo.getEmpno());

		BoardList boardlist = new BoardList(PageSize, totalCount, currentPage);

		HashMap<String, Integer> myhmap = new HashMap<String, Integer>();
		myhmap.put("startNo", boardlist.getStartNo());
		myhmap.put("endNo", boardlist.getEndNo());
		myhmap.put("empno", vo.getEmpno());

		boardlist.setList(mapper.mywrite_selectList(myhmap));

		model.addAttribute("BoardList", boardlist);

		return "mypage/mywrite_view";
	}

	// 내가 쓴 글 보기
	@RequestMapping("/mywrite_content_view")
	public String mywrite_content_view(HttpServletRequest request, Model model) {
		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);

		int idx = Integer.parseInt(request.getParameter("idx"));

		BoardVO boardvo = mapper.selectContentByIdx(idx);

		model.addAttribute("BoardVO", boardvo);
		model.addAttribute("enter", "\r\n");

		return "mypage/mywrite_content_view";
	}

	// 받은 메일
	@RequestMapping("/message_receive_view")
	public String message_receive_view(HttpServletRequest request, Model model) {

		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);

		HttpSession session = request.getSession();
		EmpVO vo = (EmpVO) session.getAttribute("EmpVO");
		currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {

		}

		int pageSize = 8;
		int totalCount = mapper.receiveMessageSelectCount(vo.getEmpno());
		int noreadCount = mapper.noreadCount(vo.getEmpno());

		MessageList messageList = new MessageList(pageSize, totalCount, currentPage);

		param.setStartNo(messageList.getStartNo());
		param.setEndNo(messageList.getEndNo());
		param.setEmpno(vo.getEmpno());

		messageList.setList(mapper.receiveMessageSelectList(param));

		model.addAttribute("Read", noreadCount);
		model.addAttribute("MessageList", messageList);
		return "mypage/message_receive_view";
	}

	// 보낸 메일
	@RequestMapping("/message_send_view")
	public String message_send_view(HttpServletRequest request, Model model) {

		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);

		HttpSession session = request.getSession();
		EmpVO vo = (EmpVO) session.getAttribute("EmpVO");
		currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {

		}

		int pageSize = 8;
		int totalCount = mapper.sendMessageSelectCount(vo.getEmpno());
		int noreadCount = mapper.noreadCount(vo.getEmpno());

		MessageList messageList = new MessageList(pageSize, totalCount, currentPage);

		param.setStartNo(messageList.getStartNo());
		param.setEndNo(messageList.getEndNo());
		param.setEmpno(vo.getEmpno());

		messageList.setList(mapper.sendMessageSelectList(param));

		model.addAttribute("Read", noreadCount);
		model.addAttribute("MessageList", messageList);
		return "mypage/message_send_view";
	}

	// 휴지통
	@RequestMapping("/message_trash_view")
	public String message_trash_view(HttpServletRequest request, Model model) {

		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);

		HttpSession session = request.getSession();
		EmpVO vo = (EmpVO) session.getAttribute("EmpVO");
		currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {

		}

		int pageSize = 8;
		int totalCount = mapper.trashMessageSelectCount(vo.getEmpno());
		int noreadCount = mapper.noreadCount(vo.getEmpno());

		MessageList messageList = new MessageList(pageSize, totalCount, currentPage);

		param.setStartNo(messageList.getStartNo());
		param.setEndNo(messageList.getEndNo());
		param.setEmpno(vo.getEmpno());

		messageList.setList(mapper.trashMessageSelectList(param));

		model.addAttribute("Read", noreadCount);
		model.addAttribute("MessageList", messageList);
		return "mypage/message_trash_view";
	}

	// 쪽지 글 보기
	@RequestMapping("/message_content_list")
	public String message_content_list(HttpServletRequest request, Model model) {

		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);

		int idx = Integer.parseInt(request.getParameter("idx"));

		MessageVO messageVO = mapper.messageSelectByIdx(idx);

		model.addAttribute("divs", request.getParameter("divs"));
		request.setAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		request.setAttribute("MessageVO", messageVO);
		request.setAttribute("enter", "\r\n");

		return "mypage/message_content_view";
	}

	// 받은 쪽지함에서 글 보기(status 변경)
	@RequestMapping("/read_message_content_list")
	public String read_message_content_list(HttpServletRequest request, Model model) {

		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);

		int idx = Integer.parseInt(request.getParameter("idx"));

		MessageVO messageVO = mapper.messageSelectByIdx(idx);
		mapper.updateStatus(idx);

		model.addAttribute("divs", request.getParameter("divs"));
		request.setAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		request.setAttribute("MessageVO", messageVO);
		request.setAttribute("enter", "\r\n");

		return "mypage/message_content_view";
	}

	// 쪽지 삭제관련
	@RequestMapping("/message_service")
	public String mailboxes_service(HttpServletRequest request, Model model, MessageVO meo) {

		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);

		int mode = Integer.parseInt(request.getParameter("mode"));

		if (mode == 2) { // 메일 삭제

			mapper.messageGoTrash(meo);
			return "redirect:message_receive_view";

		} else if (mode == 3) { // 메일 복구

			mapper.messageRestore(meo);
			return "redirect:message_trash_view";

		} else if (mode == 4) { // 메일 찐삭제

			mapper.messageDelete(meo);
			return "redirect:message_trash_view";

		}

		return "error";
	}
	
	@RequestMapping("/message_insert")
	public String mailboxes_insert(HttpServletRequest request, Model model) {
		return "mypage/message_insert";
	}

	// 쪽지 전송
	@RequestMapping("/transMessage")
	public void transMessage(MultipartHttpServletRequest request, HttpServletResponse response, Model model,
			MessageVO vo) throws IOException {

		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);

		String receiver = request.getParameter("receiver");
		try {
			int empno = Integer.parseInt(receiver.split("[\\(\\)]")[1]);
			vo.setReceiveempno(empno);
		} catch (NullPointerException e) {

		}

		String rootUploarDir = "D:" + File.separator + "upload"; // 업로드하는 파일이 저장될 디렉토리
		File dir = new File(rootUploarDir);
		UUID uuid = UUID.randomUUID();

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 업로드 디렉토리가 존재하지 않을 경우 업로드 디렉토리를 만든다.
		// File 클래스 객체 dir에 디렉토리가 존재하지 않을 경우 mkdirs() 메소드로 디렉토리를 만든다.
		if (!dir.exists()) {
			dir.mkdir();
		}

		// 업로드 되는 파일 정보 수집
		Iterator<String> iterator = request.getFileNames();
		MultipartFile multipartFile = null;
		String realfilename = ""; // 실제 업로드 파일명
		String attachedfile = ""; // 원래 파일명

		while (iterator.hasNext()) {
			realfilename = iterator.next(); // 실제 업로드 파일명
			multipartFile = request.getFile(realfilename);
			attachedfile = multipartFile.getOriginalFilename();
			
			if (attachedfile != null && attachedfile.length() != 0) {
				try {
					// MultipartFile 인터페이스 객체에서 transferTo() 메소드로 파일을 File 객체로 만들어 업로드 한다.
					// C:/Upload/orgFileName, transferTo()로 원하는 위치에 저장
					multipartFile.transferTo(new File(dir + File.separator + uuid.toString() + "_" + attachedfile)); 
					vo.setAttachedfile(attachedfile);
					vo.setRealfilename(uuid.toString() + "_" + attachedfile);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (vo.getTitle() == null || vo.getTitle().length() == 0) {
			out.println("<script>alert('제목을 입력하세요.')</script>");
			out.println("<script>history.back(-1);</script>");
			out.flush();
		} else if (vo.getContent() == null || vo.getContent().length() == 0) {
			out.println("<script>alert('내용을 입력하세요.')</script>");
			out.println("<script>history.back(-1);</script>");
			out.flush();
		} else if (vo.getReceiveempno() == 0) {
			out.println("<script>alert('받는사람을 입력하세요.')</script>");
			out.println("<script>history.back(-1);</script>");
			out.flush();
		} else {
			mapper.sendmessage(vo);
			out.println("<script>alert('전송 완료');</script>");
			if (receiver == null) { // 쪽지함 -> 쪽지 전송으로 넘어온 경우
				out.println("<script>location.href='message_send_view';</script>");
			} else { // 쪽지 전송 모달로 전송한 경우
				out.println("<script>history.back(-1);</script>");
			}
			out.flush();
		}

	}

	// 오늘 할 일
	@RequestMapping("/todo_view")
	public String todo_view(HttpServletRequest request, Model model) {
		return "mypage/todo_view";
	}

}
