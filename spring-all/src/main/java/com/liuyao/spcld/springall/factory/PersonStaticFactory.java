package com.liuyao.spcld.springall.factory;

import com.liuyao.spcld.springall.eneity.Person;

public class PersonStaticFactory {

    public static Person getInstance() {
        final Person person = new Person();
        person.setId(1);
        person.setName("static factory person");
        person.setAge(16);
        return person;
    }
}
