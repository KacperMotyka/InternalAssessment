package com.company;


import com.company.LOGIC.ApplicationLogic;

public class Main {

    public static void main(String[] args) {
        ApplicationLogic app = new ApplicationLogic();
        ApplicationLogic.menuGame(app.lotto, app.miniLotto);
    }
}
