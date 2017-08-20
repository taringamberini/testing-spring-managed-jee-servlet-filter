package com.taringamberini.blog.post.testingspringmanagedjeeservletfilter;

import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Tarin Gamberini (www.taringamberini.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MyFilterTest {

  private MyFilter instance;
  private MockHttpServletRequest mockRequest;
  private MockHttpServletResponse mockResponse;
  private MockFilterChain mockChain;
  @Autowired
  private WebApplicationContext wac;

  @Before
  public void setUp() {
    mockRequest = new MockHttpServletRequest();
    mockResponse = new MockHttpServletResponse();
    mockChain = new MockFilterChain();
    //
    // MyFilter is not Spring managed, therefore something else instantiates
    // it: a servlet container, a test setUp method, etc ...
    instance = new MyFilter();
    instance.init(new MockFilterConfig(wac.getServletContext()));
  }

  @Test
  public void testDoFilter() throws IOException, ServletException {
    System.out.println("doFilter");
    String expected = MyFilter.UNKOWN;

    instance.doFilter(mockRequest, mockResponse, mockChain);

    Assert.assertEquals(
            "WHEN not any [" + MyFilter.WHO_REQUEST_PARAMETER + "] request"
            + " parameter is present"
            + " THEN the filter must set the [" + MyFilter.UNKOWN + "]"
            + " default value in the"
            + " [" + MyFilter.PERSON_NAME_REQUEST_ATTRIBUTE + "] request"
            + " attribute, because the View expects a value in that attribute.",
            expected,
            mockRequest.getAttribute(MyFilter.PERSON_NAME_REQUEST_ATTRIBUTE)
    );
  }

  @Configuration
  static class ContextConfiguration {

    @Bean
    public PersonDAO personDAO() {
      return Mockito.mock(PersonDAO.class);
    }

    @Bean
    public PersonSrv personSrv() {
      return Mockito.mock(PersonSrv.class);
    }

  }

}
