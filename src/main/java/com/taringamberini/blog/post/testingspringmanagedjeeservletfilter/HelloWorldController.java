package com.taringamberini.blog.post.testingspringmanagedjeeservletfilter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Tarin Gamberini (www.taringamberini.com)
 */
@Controller
public class HelloWorldController {

  @RequestMapping("/helloworld")
  public String helloworld() {
    return "helloworld";
  }

}
