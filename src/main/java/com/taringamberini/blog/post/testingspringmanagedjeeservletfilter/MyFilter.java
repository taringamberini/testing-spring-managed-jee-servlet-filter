package com.taringamberini.blog.post.testingspringmanagedjeeservletfilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tarin Gamberini (www.taringamberini.com)
 */
public class MyFilter implements Filter {

  private PersonSrv personSrv;

  private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);

  @Override
  public void init(FilterConfig filterConfig) {
    LOGGER.debug("initialized Servlet Filter");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String personId = request.getParameter("who");
    if (personId == null || personId.trim().isEmpty()) {
      request.setAttribute("personName", "unkown");
    } else {
      PersonSrv personSrv = new PersonSrv();
      Person person = personSrv.find(Long.parseLong(personId));
      if (personId == null || personId.trim().isEmpty()) {
        request.setAttribute("personName", "unkown");
      } else {
        request.setAttribute("personName", person.getName());
      }
    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
  }

}
