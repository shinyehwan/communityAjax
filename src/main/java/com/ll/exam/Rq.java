package com.ll.exam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Rq {
	private final HttpServletRequest req;
	private final HttpServletResponse resp;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;

		try {
			req.setCharacterEncoding("UTF-8"); // 들어오는 데이터를 UTF-8 로 해석하겠다.
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		resp.setCharacterEncoding("UTF-8"); // 완성되는 HTML의 인코딩을 UTF-로 하겠다.
		resp.setContentType("text/html; charset=utf-8"); // 브라우저에게 우리가 만든 결과물이 UTF-8 이다 라고 알리는 의미
	}

	public int getIntParam(String paramName, int defaultValue) {
		String value = req.getParameter(paramName);

		if (value == null) {
			return defaultValue;
		}

		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void println(String str) {
		print(str + "\n");
	}

	public void setAttr(String name, Object value) {
		req.setAttribute(name, value);
	}

	public void view(String path) {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/" + path + ".jsp");
		try {
			requestDispatcher.forward(req, resp);
		} catch (ServletException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getMethod() {
		return req.getMethod();
	}

	public String getPath() {
		return req.getRequestURI();
	}

	public String getActionPath() {
		String[] bits = req.getRequestURI().split("/");
		return "/%s/%s/%s".formatted(bits[1], bits[2], bits[3]);
	}

	public String getParam(String paramName, String defaultValue) {
		String value = req.getParameter(paramName);

		if (value == null || value.trim().length() == 0) {
			return defaultValue;
		}

		return value;
	}

	public long getLongPathValueByIndex(int index, long defaultValue) {
		String value = getPathValueByIndex(index, null);

		if (value == null) {
			return defaultValue;
		}

		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}



	}

	public String getPathValueByIndex(int index, String defaultValue) {
		String[] bits = req.getRequestURI().split("/");
		try {
			return bits[4 + index];
		} catch (ArrayIndexOutOfBoundsException e) {
			return defaultValue;
		}
	}

	public void replace(String uri, String msg) {
		if (msg != null && msg.trim().length() > 0) {
			println("""
                     <script>
                     alert("%s");
                     </script>
                     """.formatted(msg));
		}

		println("""
                 <script>
                 location.replace("%s");
                 </script>
                 """.formatted(uri));
	}

	public void historyBack(String msg) {

		if (msg != null && msg.trim().length() > 0) {
			println("""
                     <script>
                     alert("%s");
                     </script>
                     """.formatted(msg));
		}

		println("""
                 <script>
                 history.back("%s");
                 </script>
                 """.formatted(msg));
	}
}