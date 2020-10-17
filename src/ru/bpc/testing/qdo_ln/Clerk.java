package ru.bpc.testing.qdo_ln;

import java.util.Deque;
import java.util.LinkedList;

public class Clerk {
    private PersonFactory personFactory;

    public Clerk(King king) {
        this.personFactory = new PersonFactory(king);
    }

    public PersonFactory getPersonFactory() {
        return personFactory;
    }

    public void setPersonFactory(PersonFactory personFactory) {
        this.personFactory = personFactory;
    }

    public void createHierarchy(String... singleReports){
        for (String s: singleReports) {
            Deque<Integer> spacers = null;
            try {
                spacers = checkReportAndFindSpacers(s);
            }
            catch (IllegalArgumentException e){
                e.printStackTrace();
            }
            if(spacers.isEmpty()){
                personFactory.createPerson(s);
            }
            else {
                int beginIndex = 0;
                int endIndex = spacers.peekFirst();
                Person person = personFactory.createPerson(s.substring(beginIndex, endIndex));
                while (spacers.size() >= 2){
                    beginIndex = spacers.pollFirst() + 2;
                    endIndex = spacers.peekFirst();
                    personFactory.addSlave(person, s.substring(beginIndex, endIndex));
                }
            }
        }
    }

    private Deque<Integer> checkReportAndFindSpacers(String singleReport){
        char[] report = singleReport.toCharArray();
        Deque<Integer> spacers = new LinkedList<>();
        for (int i = 0; i < report.length; i++) {
            if(report[i] == ':'){
                if(spacers.isEmpty()){
                    spacers.addLast(i);
                }else{
                    throw new IllegalArgumentException("Incorrect single report format");
                }
            }
            else if(report[i] == ','){
                if(spacers.isEmpty()){
                    throw new IllegalArgumentException("Incorrect single report format");
                }
                else{
                    spacers.addLast(i);
                }
            }
        }
        return spacers;
    }

    public void printHierarchy(Person person){
            printHierarchyHelper(person, new StringBuilder());
    }

    private void printHierarchyHelper(Person person, StringBuilder space){
        System.out.println(space + person.getName());
        if(!person.getSlaves().isEmpty()){
            space.append('\t');
            for (Person p: person.getSlaves()) {
                printHierarchyHelper(p, space);
            }
            space.deleteCharAt(0);
        }
    }
}
