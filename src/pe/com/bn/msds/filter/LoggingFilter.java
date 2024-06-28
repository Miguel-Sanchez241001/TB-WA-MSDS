package pe.com.bn.msds.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import pe.com.bn.msds.common.LoggerBn;

public class LoggingFilter extends GenericFilterBean {

	private static LoggerBn log3 = LoggerBn.getInstance(LoggingFilter.class.getName());
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		ContentCachingRequestWrapper wreq = new ContentCachingRequestWrapper((HttpServletRequest) request);

		ContentCachingResponseWrapper wres = new ContentCachingResponseWrapper((HttpServletResponse) response);

		// let it be ...
		filterChain.doFilter(wreq, wres);

		// makes sure that the input is read (e.g. in 404 it may not be)
		while (wreq.getInputStream().read() >= 0);

		log3.tracer("=== REQUEST ===" + new String(wreq.getContentAsByteArray()));
		
		// Do whatever logging you wish here, in this case I'm writing request
		// and response to system out which is probably not what you wish to do
		log3.tracer("=== RESPONSE ===" + new String(wres.getContentAsByteArray()));;

		// this is specific of the "ContentCachingResponseWrapper" we are relying on,
		// make sure you call it after you read the content from the response
		wres.copyBodyToResponse();

		// One more point, in case of redirect this will be called twice! beware to
		// handle that
		// somewhat
	}

}
