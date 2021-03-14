package ru.vorobyov;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        //вызов метода с основной логикой
        System.out.println(new StringConverter().run(input));


    }
}
