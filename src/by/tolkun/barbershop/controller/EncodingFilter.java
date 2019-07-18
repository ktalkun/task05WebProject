package by.tolkun.barbershop.controller;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
	private String code;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		//TODO: Set code from property file
		code = "UTF-8";
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		String codeRequest = request.getCharacterEncoding();
		if (code != null && !code.equalsIgnoreCase(codeRequest)) {
			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);

		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		code = null;
	}
}
