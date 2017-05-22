package com.taringamberini.blog.post.testingspringmanagedjeeservletfilter;

import org.springframework.stereotype.Repository;

/**
 * @author Tarin Gamberini (www.taringamberini.com)
 */
@Repository
public class PersonDAO {

  // datasource
  public Person find(Long id) {
    // Suposing
    //
    //   select * from PERSON P where P.ID = :id
    //
    // returns the following person
    Person person = new Person();
    person.setId(1L);
    person.setName("Galileo");
    return person;
  }

}
