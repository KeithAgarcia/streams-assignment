package com.theironyard.charlotte;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    //method distinct
    //reduce method

    public static void main(String[] args) {
        List<User> users =
                Stream.generate(User::createUser).limit(500).collect(Collectors.toList());

        // given "users" as defined above, print each of the following using Streams:
        // 1: An ordered list of every user's age, filtered by whether they're older than 65
        List<Integer> usersAge = users.stream()
                // .filter removes all people who are 65 or younger
                .filter(u -> u.getAge() > 65)

                // .sorted takes a comparator object and is used to
                // sort the stream
                .sorted(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return (int) (o2.getAge() - o1.getAge());
                    }
                })

                // we are mapping the stream from holding users
                // to holding ints
                .map(u -> u.getAge())

                // we are converting the stream to a list
                .collect(Collectors.toList());


        // 2: A list of all the users whose names are longer than 10 characters
        List<String> usersName = users.stream()
                .filter(u -> u.getName().length() > 10)
                .sorted(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o2.getName().compareTo(o1.getName());
                    }
                })
                .map(u -> u.getName())
                .collect(Collectors.toList());


        // 3: A list of all users that have extensions in their phone numbers (e.g. x5209)
        List<String> usersNumber = users.stream()
                .filter(u -> u.getPhoneNumber().contains("x"))
                .sorted(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o2.getPhoneNumber().compareTo(o2.getPhoneNumber());
                    }
                })
                .map(u -> u.getPhoneNumber())
                .collect(Collectors.toList());



        // 4: An alphabetical list of all the distinct states available in the list of users (at most 50)
        List<String> usersState = users.stream()
                .distinct()
                .sorted(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.getState().compareTo(o2.getState());
                    }
                })
                .map(u -> u.getState())
                .collect(Collectors.toList());



        // 5: The sum of all the ages of the users in the list.
        Integer totalAge = users.stream()
                .mapToInt(User::getAge)
                .sum();


        // HARD MODE:
        // 6: Convert the list into a HashMap, where the Key is the state the User is from,
        // and the value is a list of users from said state.
}}
