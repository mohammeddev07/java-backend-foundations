package org.foundationsofcode.service;

public class StackHeapDemo {

    static class Box {
        int value;
        Box(int value) { this.value = value; }
    }
    public static void main(String[] args) {
        int x= 10;
        Box a = new Box(5);
        Box b = a;
        mutate(x, a);

        System.out.println("x=" + x);
        System.out.println("a.value=" + a.value);
        System.out.println("b.value=" + b.value);
    }
    static void mutate(int x, Box box) {
        x = 99;
        box.value = 99;
    }
}
