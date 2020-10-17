package ru.bpc.testing.qdo_ln;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {
    PersonFactory personFactory;

    @Before
    public void init(){
        personFactory = new PersonFactory(new King("Igor"));
    }

    @Test
    public void creatSingleReportTest(){
        Person person = personFactory.createPerson("Dream");
        personFactory.addSlave(person, "Space");
        personFactory.addSlave(person, "Frank");

        String expect = "Dream: Frank, Space, ";
        Assert.assertEquals(expect, person.createSingleReport());
    }
}
