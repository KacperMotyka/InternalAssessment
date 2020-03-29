package com.company.LOGIC;

import com.company.DATA_ACCESS.DataReader;
//import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataManager {

    public Game lotto;
    public Game miniLotto;
    public Game multiLotto;
    public Game euroJack;

    public DataManager() {

        // REFRESH DATA
        // DataDownloader.refreshData();

        // READ DATA
        String fileNameOne = "lotto-history.txt";
        BufferedReader lottoFile = DataReader.readFile (fileNameOne);
        ArrayList<Draw> lottoHistory = DataReader.parseFileContent (lottoFile);

        String fileNameTwo = "mini-lotto-history.txt";
        BufferedReader miniLottoFile = DataReader.readFile (fileNameTwo);
        ArrayList<Draw> miniLottoHistory = DataReader.parseFileContent (miniLottoFile);

        String fileName3 = "multi-lotto-history.txt";
        BufferedReader multiLottoFile = DataReader.readFile (fileName3);
        ArrayList<Draw> multiLottoHistory = DataReader.parseFileContent (multiLottoFile);


        this.lotto = new Game ("Lotto", "../../../../img/lotto.png", "1957-03-07", 49, 6, lottoHistory);
        this.miniLotto = new Game ("Mini Lotto", "../../../../img/miniLotto.png", "30-12-1981", 42, 5, miniLottoHistory);
        this.multiLotto = new Game ("Multi Lotto", "../../../../img/multiLotto.png", "18-03-1996", 80, 20, multiLottoHistory);

        Draw draw1 = new Draw(1, "21-02-2020", new ArrayList<Integer> (Arrays.asList(1,2,7,11,34,16)));
        Draw draw2 = new Draw(2, "28-02-2020", new ArrayList<Integer> (Arrays.asList(1,22,17,12,24,36)));
        Draw draw3 = new Draw(3, "25-03-2020", new ArrayList<Integer> (Arrays.asList(9,23,5,11,37,6)));

        this.euroJack = new Game ("Euro Jack", "../../../../img/euroJack.png", "30-12-2000", 49, 6, new ArrayList<Draw>(Arrays.asList(draw1, draw2, draw3, draw1, draw2, draw3, draw1, draw2, draw3, draw1, draw2, draw3, draw1, draw2, draw3, draw1, draw2, draw3, draw1, draw2, draw3, draw1, draw2, draw3)));

    }


}
