package org.example;

public class Example implements Testing {
    public void show(String s) {
        System.out.println("String s = " + s);
    }

    public void showExample() {
        System.out.println("This is a showExample");
    }

    public static void main(String[] args) {
        Testing obj = new Example();
        Example obj2 = new Example();

        //obj.showExample(); Ошибка: объект с типом Testing не знает о существовании метода showExample()
        obj2.showExample();
    }
}
