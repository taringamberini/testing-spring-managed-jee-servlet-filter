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
@Component
public class MyFilter implements Filter {

  public static final String PERSON_NAME_REQUEST_ATTRIBUTE = "personName";
  public static final String UNKOWN = "unkown";
  public static final String WHO_REQUEST_PARAMETER = "who";
  private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);
  @Autowired
  private PersonSrv personSrv;

  @Override
  public void init(FilterConfig filterConfig) {
    SpringBeanAutowiringSupport.
            processInjectionBasedOnServletContext(
                    this, filterConfig.getServletContext());
    LOGGER.debug("initialized Spring managed Servlet Filter");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
          FilterChain chain) throws IOException, ServletException {
    String personId = request.getParameter(WHO_REQUEST_PARAMETER);
    if (personId == null || personId.trim().isEmpty()) {
      request.setAttribute(PERSON_NAME_REQUEST_ATTRIBUTE, UNKOWN);
    } else {
      Person person = personSrv.find(Long.parseLong(personId));
      if (personId == null || personId.trim().isEmpty()) {
        request.setAttribute(PERSON_NAME_REQUEST_ATTRIBUTE, UNKOWN);
      } else {
        request.setAttribute(PERSON_NAME_REQUEST_ATTRIBUTE, person.getName());
      }
    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
  }

}
