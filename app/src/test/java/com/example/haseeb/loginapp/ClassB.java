package com.example.haseeb.loginapp;


public class ClassB {

    private OperationListener listener;

    ClassB(int a, int b, OperationListener listener) throws InterruptedException {
        this.listener = listener;

        Thread.sleep(2000);

        listener.add(a + b);
        listener.sub(a - b);
    }

}
