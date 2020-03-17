package com.company;


import com.company.LOGIC.Application;

public class Main {

    public static void main(String[] args) {
        Application app = new Application();
        Application.menuGame(app.lotto, app.miniLotto);
    }
}
