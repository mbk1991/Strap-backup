package com.kh.strap.admin.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.strap.admin.domain.Admin;
import com.kh.strap.admin.domain.Notice;
import com.kh.strap.admin.service.NoticeService;
import com.kh.strap.member.domain.Member;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService nService;
	
	/**
	 * 
	 * @param mv
	 * @param page
	 * @return
	 */
	// 관리자 공지사항 리스트
	@RequestMapping(value="/admin/noticeListView.strap", method=RequestMethod.GET)
	public String showAdminNoticeList(@RequestParam(value="page", required=false) Integer page
			, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginUser");
		if(member != null) {
			int currentPage = (page != null) ? page : 1;
			int totalCount = nService.getTotalCount("","");
			int noticeLimit = 10;
			int naviLimit = 5;
			int maxPage;
			int startNavi;
			int endNavi;
			maxPage = (int)((double)totalCount/noticeLimit + 0.9);
			startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
			endNavi = startNavi + naviLimit - 1;
			if(maxPage < endNavi) {
				endNavi = maxPage;
			}
			List<Notice> nList = nService.printNoticeList(currentPage, noticeLimit);
			if(!nList.isEmpty()) {
				request.setAttribute("urlVal", "noticeListView");
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("noticeLimit", noticeLimit);
				request.setAttribute("startNavi", startNavi);
				request.setAttribute("endNavi", endNavi);
				request.setAttribute("nList", nList);
			}
			return("admin/adminNoticeList");
		} else {
			request.setAttribute("msg", "로그인후 이용 가능한 서비스입니다.");
			request.setAttribute("url", "/admin/loginView.strap");
			return("common/alert");
		}
	}
	
	/**
	 * 
	 * @param mv
	 * @return
	 */
	// 관리자 공지사항 작성페이지
	@RequestMapping(value="/admin/writeView.strap", method=RequestMethod.GET)
	public ModelAndView showAdminNoticeWrite(ModelAndView mv) {
		mv.setViewName("admin/adminNoticeWrite");
		return mv;
	}
	/**
	 * 
	 * @param mv
	 * @param notice
	 * @param uploadFile
	 * @param request
	 * @return
	 */
	// 관리자 공지사항 등록
	@RequestMapping(value="/admin/registerNotice.strap", method=RequestMethod.POST)
	public ModelAndView registNotice(ModelAndView mv
			, @ModelAttribute Notice notice
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginUser");
		String memberName = member.getMemberNick();
		notice.setNoticeWriter(memberName);
		try {
			String noticeFilename = uploadFile.getOriginalFilename();
			if(!noticeFilename.equals("")) {
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savePath = root + "\\nuploadFiles";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String noticeFileRename = sdf.format(new Date(System.currentTimeMillis()))+"."+noticeFilename.substring(noticeFilename.lastIndexOf(".")+1);
				File file = new File(savePath);
				if(!file.exists()) { 
					file.mkdir();
				}
				uploadFile.transferTo(new File(savePath+"\\"+noticeFileRename));
				String noticeFilepath = savePath+"\\"+noticeFileRename;
				notice.setNoticeFilename(noticeFilename);
				notice.setNoticeFileRename(noticeFileRename);
				notice.setNoticeFilepath(noticeFilepath); 
			}
			int result = nService.registerNotice(notice);
			request.setAttribute("msg","(관리자) 공지사항이 등록되었습니다.");
			request.setAttribute("url","/admin/noticeListView.strap");
			mv.setViewName("/common/alert");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	/**
	 * 
	 * @param mv
	 * @param noticeNo
	 * @param page
	 * @param session
	 * @return
	 */
	// 관리자 공지사항 상세페이지 조회 & 조회수 중복 방지
	@RequestMapping(value="/admin/noticeDetailView.strap", method=RequestMethod.GET)
	public ModelAndView noticeDetailView(ModelAndView mv
			, @RequestParam("noticeNo") Integer noticeNo
			, @RequestParam("page") Integer page
			, HttpSession session
			,HttpServletRequest request
			,HttpServletResponse response) {
		try {
			Notice notice = nService.printOneByNo(noticeNo);
			session.setAttribute("noticeNo", notice.getNoticeNo());
			mv.addObject("notice", notice);
			mv.addObject("page", page);
			
			Cookie [] cookies = request.getCookies();
	        Cookie viewCookie = null;
	        
	        if (cookies != null && cookies.length > 0) {
	            for (int i = 0; i < cookies.length; i++) {
	                if (cookies[i].getName().equals("cookie" + noticeNo)) { 
	                    viewCookie = cookies[i];
	                }
	            }
	        }
	        if (notice != null) {
	            mv.addObject("page", page);
	        	mv.addObject("notice", notice);
	            if (viewCookie == null) {    
	                Cookie newCookie = new Cookie("cookie"+noticeNo, "|" + noticeNo + "|");
	                response.addCookie(newCookie);
	                int result = nService.viewUp(noticeNo);
	            } else { 
	                String value = viewCookie.getValue();
	            }
	        }
			mv.setViewName("admin/adminNoticeDetailView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	/**
	 * 
	 * @param session
	 * @param model
	 * @param page
	 * @return
	 */
	// 관리자 공지사항 삭제
	@RequestMapping(value="/admin/removeNotice.strap", method=RequestMethod.GET)
	public String noticeRemove(HttpSession session, Model model
			, @RequestParam("page") Integer page) {
		try {
			int noticeNo = (Integer)session.getAttribute("noticeNo");
			int result = nService.removeOneByNo(noticeNo);
			if(result > 0) {
				session.removeAttribute("noticeNo");
			}
			return "redirect:/admin/noticeListView.strap?page="+page;
		} catch (Exception e) {
			model.addAttribute("msg", e.toString());
			return "common/errorPage";
		}
	}
	
	/**
	 * 
	 * @param mv
	 * @param noticeNo
	 * @param page
	 * @return
	 */
	// 관리자 공지사항 수정 페이지
	@RequestMapping(value="/admin/modifyView.strap", method=RequestMethod.GET)
	public ModelAndView noticeModifyView(
			ModelAndView mv
			, @RequestParam("noticeNo") Integer noticeNo
			, @RequestParam("page") int page) {
		try {
			Notice notice = nService.printOneByNo(noticeNo);
			mv.addObject("notice", notice);
			mv.addObject("page", page);
			mv.setViewName("admin/adminNoticeModifyForm");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	/**
	 * 
	 * @param notice
	 * @param mv
	 * @param reloadFile
	 * @param page
	 * @param request
	 * @return
	 */
	// 관리자 공지사항 수정
	@RequestMapping(value="/admin/noticeModify.strap", method=RequestMethod.POST)
	public ModelAndView noticeModify(@ModelAttribute Notice notice
			, ModelAndView mv
			,@RequestParam(value="reloadFile", required=false) MultipartFile reloadFile
			,@RequestParam("page") Integer page
			,HttpServletRequest request) {
		try {
			String noticeFilename = reloadFile.getOriginalFilename();
			if(reloadFile != null && !noticeFilename.equals("")) {
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savedPath = root + "\\nuploadFiles";
				File file = new File(savedPath + "\\" + notice.getNoticeFileRename());
				if(file.exists()) {
					file.delete();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String noticeFileRename = sdf.format(new Date(System.currentTimeMillis())) + "." 
				+ noticeFilename.substring(noticeFilename.lastIndexOf(".")+1);
				String noticeFilepath = savedPath + "\\" + noticeFileRename;
				reloadFile.transferTo(new File(noticeFilepath));
				notice.setNoticeFilename(noticeFilename);
				notice.setNoticeFileRename(noticeFileRename);
				notice.setNoticeFilepath(noticeFilepath);
			}
			int result = nService.modifyNotice(notice);
			mv.setViewName("redirect:/admin/noticeDetailView.strap?noticeNo="+notice.getNoticeNo()+"&page="+page);
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	/**
	 * 
	 * @param mv
	 * @param searchCondition
	 * @param searchValue
	 * @param page
	 * @return
	 */
	// 관리자 공지사항 조건별 검색
	@RequestMapping(value="/admin/noticeSearch.strap", method=RequestMethod.GET)
	public ModelAndView noticeSearchList(ModelAndView mv
			, @RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchValue") String searchValue
			, @RequestParam(value="page", required=false) Integer page) {
		try {
			int currentPage = (page != null) ? page : 1;
			int totalCount = nService.getTotalCount(searchCondition, searchValue);
			int noticeLimit = 10;
			int naviLimit = 5;
			int maxPage;
			int startNavi;
			int endNavi;
			maxPage = (int)((double)totalCount/noticeLimit + 0.9);
			startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
			endNavi = startNavi + naviLimit - 1;
			if(maxPage < endNavi) {
				endNavi = maxPage;
			}
			List<Notice> nList = nService.printAllByValue(
					searchCondition, searchValue, currentPage, noticeLimit);
			if(!nList.isEmpty()) {
				mv.addObject("nList", nList);
			}else {
				mv.addObject("nList", null);
			}
				mv.addObject("urlVal", "search");
				mv.addObject("searchCondition", searchCondition);
				mv.addObject("searchValue", searchValue);
				mv.addObject("maxPage", maxPage);
				mv.addObject("currentPage", currentPage);
				mv.addObject("startNavi", startNavi);
				mv.addObject("endNavi", endNavi);
				mv.setViewName("admin/adminNoticeList");
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
}
