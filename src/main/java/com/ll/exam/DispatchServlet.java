package com.ll.exam;

import java.io.IOException;
import java.lang.reflect.Member;
import java.util.Map;

import com.ll.exam.article.ArticleController;
import com.ll.exam.member.MemberController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/usr/*")
public class DispatchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Rq rq = new Rq(req, resp);

		MemberController memberController = new MemberController();
		ArticleController articleController = new ArticleController();

		switch (rq.getMethod()) {
			case "GET":
				switch (rq.getActionPath()) {
					case "/usr/article/modify":
						articleController.showModify(rq);
						break;
					case "/usr/article/detail":
						articleController.showDetail(rq);
						break;
					case "/usr/article/list":
						articleController.showList(rq);
						break;
					case "/usr/article/write":
						articleController.showWrite(rq);
						break;
					case "/usr/member/login":
						memberController.showLogin(rq);
						break;
				}
				break;
			case "POST":
				switch (rq.getActionPath()) {
					case "/usr/article/write":
						articleController.doWrite(rq);
						break;
					case "/usr/article/modify":
						articleController.doModify(rq);
						break;
					case "/usr/article/delete":
						articleController.doDelete(rq);
						break;
				}
				break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		doGet(req, resp);
	}

}


