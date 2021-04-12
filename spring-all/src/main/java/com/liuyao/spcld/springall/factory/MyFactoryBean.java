package com.liuyao.spcld.springall.factory;

import com.liuyao.spcld.springall.eneity.Person;
import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<Person> {
    @Override
    public Person getObject() throws Exception {
        final Person person = new Person();
        person.setId(3);
        person.setName("factorybean person");
        person.setAge(16);
        return person;
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
