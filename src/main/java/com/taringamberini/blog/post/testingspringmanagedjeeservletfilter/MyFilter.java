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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * @author Tarin Gamberini (www.taringamberini.com)
 */
@Component()
public class MyFilter implements Filter {

  @Autowired
  private PersonSrv personSrv;

  private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);

  @Override
  public void init(FilterConfig filterConfig) {
    SpringBeanAutowiringSupport.
            processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    LOGGER.debug("initialized Spring managed Servlet Filter");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String personId = request.getParameter("who");
    if (personId == null || personId.trim().isEmpty()) {
      request.setAttribute("personName", "unkown");
    } else {
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
