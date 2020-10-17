package ru.bpc.testing.qdo_ln;

import java.util.Comparator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class Person {
    protected String name;
    private SortedSet<Person> slaves;

    public Person(String name) {
        this.name = name;
        this.slaves = new TreeSet<Person>(Comparator.comparing(Person::getName));
    }

    public SortedSet<Person> getSlaves() {
        return slaves;
    }

    public void setSlaves(SortedSet<Person> slaves) {
        this.slaves = slaves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String createSingleReport() {
        StringBuilder report = new StringBuilder(this.getName() + ": ");
        for (Person p:getSlaves()) {
            report.append(p.getName() + ", ");
        }
        return report.toString();
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
