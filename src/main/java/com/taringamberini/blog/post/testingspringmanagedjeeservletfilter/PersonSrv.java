package com.taringamberini.blog.post.testingspringmanagedjeeservletfilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tarin Gamberini (www.taringamberini.com)
 */
@Service
public class PersonSrv {

  @Autowired
  private PersonDAO personDAO;

  public Person find(Long id) {
    return personDAO.find(id);
  }

}
