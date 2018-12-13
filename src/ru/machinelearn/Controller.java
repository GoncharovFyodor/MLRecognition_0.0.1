package ru.machinelearn;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Controller {
    /**
     * Структура "буквосочетание-частота"
     */
    public static HashMap<String,Integer> freq;

    /**
     * "Правдоподобность" (насколько возможно, что строка написана на русском) строки
     */
    public static double probability;
    /**
     * Подсчёт частотности пар букв в русском языке
     * @param model "модель" языка
     */
    public static void freqCounting(String model){
        String large = "";
        ArrayList<String> pairs = new ArrayList<>();
        freq = new HashMap<>();
        try {
            Scanner largeScan = new Scanner(new File(model));
            while(largeScan.hasNext()) {
                large += (largeScan.nextLine()+"\n");
            }
            large=large.toLowerCase();
            for(int i=0;i<large.length()-1;i++) {
                if (large.charAt(i) >= 'а' && large.charAt(i) <= 'я')
                    if (large.charAt(i + 1) >= 'а' && large.charAt(i + 1) <= 'я') {
                        pairs.add(large.substring(i, i + 2));
                    }
            }
            Collections.sort(pairs);
            int cnt = 1;
            for(int i=0;i<pairs.size()-1;i++) {
                if(pairs.get(i).equals(pairs.get(i+1))){
                    cnt++;
                    continue;
                }
                freq.put(pairs.get(i),cnt);
                cnt=1;
            }
            largeScan.close();
        }catch(FileNotFoundException fnf){
            System.out.println("Файл "+model+" не найден!");
        }
    }
    /**
     * Подсчет "правдоподобности" текста
     * @param txt текст
     * @param model "модель" языка
     */
    public static void probCounting(String txt, String model){
        freqCounting(model);
        txt=txt.toLowerCase();
        ArrayList<String> textPairs = new ArrayList<>();
        //Выделение пар букв
        for(int i=0;i<txt.length()-1;i++) {
            if (txt.charAt(i) >= 'а' && txt.charAt(i) <= 'я')
                if (txt.charAt(i + 1) >= 'а' && txt.charAt(i + 1) <= 'я') {
                    //System.out.println(someText.substring(i, i + 2));
                    textPairs.add(txt.substring(i, i + 2));
                }
        }
        //Считаем "правдоподобность" строки
        probability=1;
        for(int i=0;i<textPairs.size();i++) {
            if(freq.containsKey(textPairs.get(i))) {
                probability *= freq.get(textPairs.get(i));
            }
        }
        probability=(Math.pow(probability,1.0/(txt.length()-1)));
    }
    public static double THRESHOLD = 68.0;

    public static void controller(){
        String detectResult;
        probCounting(Model.someText,Model.largeName);
        if(probability>=THRESHOLD){
            detectResult = "Текст написан на русском языке";
        }else{
            detectResult = "Текст написан не на русском языке";
        }
        JOptionPane.showMessageDialog(null,detectResult,"Результат",JOptionPane.INFORMATION_MESSAGE);
    }
}
