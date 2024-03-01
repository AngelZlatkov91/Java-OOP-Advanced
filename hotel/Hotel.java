package hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private int capacity;
    private List<Person> roster;

    public Hotel(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }
    public void add(Person person) {
        if(capacity > roster.size()) {
            roster.add(person);
        }
    }
    public boolean remove (String name) {
        for (Person person : roster) {
            if (person.getName().equals(name)) {
                roster.remove(person);
                return true;
            }
        }
        return false;
    }
    public Person getPerson (String name, String hometown) {
        Person person = null;
        for (Person persons: roster) {
            if (persons.getName().equals(name)&& persons.getHometown().equals(hometown)) {
                person = persons;
            }
        }
        return person;
    }
    public int getCount() {
        return roster.size();
    }
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("The people in the hotel ").append(this.name).append(" are:").append(System.lineSeparator());
        for (Person person: roster) {
            sb.append(person);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
