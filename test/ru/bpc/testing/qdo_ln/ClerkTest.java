package ru.bpc.testing.qdo_ln;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClerkTest {
    Clerk clerk;

    @Before
    public void init(){
        clerk = new Clerk(new King("Igor"));
        clerk.createHierarchy("Dream: Space, Frank, ");
    }

    @Test
    public void createHierarchyTest1(){
        Assert.assertEquals(3, clerk.getPersonFactory().getKingdomPopulation().size());
        Assert.assertTrue(clerk.getPersonFactory().getKingdomPopulation().containsKey("Dream"));
    }

    @Test
    public void createHierarchyTest2(){
        Assert.assertTrue(clerk.getPersonFactory().getKingdomPopulation().containsKey("Dream"));
        Assert.assertTrue(clerk.getPersonFactory().getKingdomPopulation().containsKey("Space"));
        Assert.assertTrue(clerk.getPersonFactory().getKingdomPopulation().containsKey("Frank"));
    }

    // вывод по алф.
}
