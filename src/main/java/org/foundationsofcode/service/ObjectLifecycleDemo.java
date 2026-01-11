package org.foundationsofcode.service;

public class ObjectLifecycleDemo {
    static {
        System.out.println("1). Static init block");
    }

    static class User {
        static int count = initCount();
        String name = initName();

        User(String name) {
            System.out.println("4). constructor start");
            this.name = name;
            System.out.println("5). constructor end");
        }

        static int initCount() {
            System.out.println("2). static field init");
            return 0;
        }

        String initName() {
            System.out.println("3). instance field init");
            return "default";
        }

        public static void main(String[] args) {
            System.out.println("--main start--");
            User u = new User("Alice");
            System.out.println("--final name=" + u.name + "--");
        }
    }
}
