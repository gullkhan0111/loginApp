package com.example.haseeb.loginapp;


public class ClassA implements OperationListener {

    ClassA(int a, int b) {

        System.out.println("Waiting...");

        try {


            new ClassB(a, b, this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void add(int result) {

    }

    @Override
    public void sub(int result) {

    }
}
