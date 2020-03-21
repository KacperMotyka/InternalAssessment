package com.company;


import com.company.LOGIC.DataManager;

public class ApplicationCommandLine {

    DataManager manager;

    public ApplicationCommandLine() {
        this.manager = new DataManager();
        DataManager.menuGame(manager.lotto, manager.miniLotto);
    }


    public static void main(String[] args) {
        ApplicationCommandLine app = new ApplicationCommandLine();
    }
}
