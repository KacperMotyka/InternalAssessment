package com.company.LOGIC;


public class ApplicationCommandLine {

    DataManager manager;

    public ApplicationCommandLine() {
        this.manager = new DataManager();
        manager.chooseGame();
    }


    public static void main(String[] args) {
        ApplicationCommandLine app = new ApplicationCommandLine();
    }
}
