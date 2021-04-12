package com.liuyao.spcld.springall.factory;

import com.liuyao.spcld.springall.eneity.Person;

public class PersonInstanceFactory {

    public Person getInstance() {
        final Person person = new Person();
        person.setId(2);
        person.setName("instance factory person");
        person.setAge(16);
        return person;
    }
}
