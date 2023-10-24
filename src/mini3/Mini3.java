/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini3;

/**
 *
 * @author kuldeepshukla
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Person {
    int id;
    String firstName;
    String lastName;
    String email;

    public Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

class Team {
    String name;
    List<Person> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addMember(Person person) {
        members.add(person);
    }

    @Override
    public String toString() {
        return "Team: " + name + "\nMembers: " + members;
    }
}

public class Mini3 {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        List<Team> teams = new ArrayList<>();

        // Read data from a file and load it into the 'people' list
        try (BufferedReader br = new BufferedReader(new FileReader("mc.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                String email = parts[3];
                people.add(new Person(id, firstName, lastName, email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Shuffle the list of people to randomize the order
        Collections.shuffle(people);

        // Create 20 teams with 5 people each
        for (int i = 0; i < 20; i++) {
            Team team = new Team("Team " + (i + 1));
            for (int j = 0; j < 5; j++) {
                int index = i * 5 + j;
                if (index < people.size()) {
                    Person person = people.get(index);
                    team.addMember(person);
                }
            }
            teams.add(team);
        }

        // Print each team and its members
        for (Team team : teams) {
            System.out.println(team);
        }
    }
}