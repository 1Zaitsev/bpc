package ru.bpc.testing.qdo_ln;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.bpc.testing.qdo_ln.King;
import ru.bpc.testing.qdo_ln.Person;
import ru.bpc.testing.qdo_ln.PersonFactory;
import ru.bpc.testing.qdo_ln.PersonWithSlaves;

public class PersonFactoryTest {
    private PersonFactory personFactory;

    @Before
    public void initClerk(){
        personFactory  = new PersonFactory(new King("Igor"));
    }

    @Test
    public void createPerson(){
        personFactory.createPerson("Simon");
        Assert.assertEquals(new PersonWithSlaves("Simon"), personFactory.getKingdomPopulation().get("Simon"));
    }

    @Test
    public void checkAddSlave(){
        Person master = personFactory.createPerson("Simon");
        personFactory.addSlave(master, "Anna");
        Assert.assertEquals(new PersonWithSlaves("Anna"), personFactory.getKingdomPopulation().get("Anna"));
        Assert.assertTrue(master.getSlaves().contains(new PersonWithSlaves("Anna")));
    }

    @Test
    public void checkClones(){
        Person master = personFactory.createPerson("Simon");
        Person clone = personFactory.createPerson("Simon");
        personFactory.createPerson("Anna");
        personFactory.addSlave(master, "Anna");

        Assert.assertEquals(2, personFactory.getKingdomPopulation().size());
    }

    // проверить раба без мастера
}
