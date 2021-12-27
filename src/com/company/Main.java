package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// скачки
        Horse[] horses = new Horse[100];
       /* horses[0] = new Horse("Юпитер");
        horses[1] = new Horse("Стрела");
        horses[2] = new Horse("Молния");
        horses[3] = new Horse("Мустанг");*/
        for (int i=0; i<100; i++){
            horses[i] = new Horse("p" + i + 1);
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество кругов?");
        Game game = new Game(horses, in.nextInt());
        game.process();
    }
}
class Horse{
    private String name;
    boolean isFail=false;
    public Horse(String name) {
        this.name = name;
    }
    //метод, возвращающий время круга в секундах от 180 до 300
    public int getLapTime(){
        boolean fail = false;
        int r = (int)(Math.random()*100);
        if (r>=0 && r<=3) fail = true;
        if (fail) {
            isFail = true;
            return -1;
        }
        return (int) (Math.random()*121 + 180);
    }
    //геттер
    public String getName() {
        return name;
    }
}
class Game{
    private Horse[] horses;
    private int lapCount;
    //конструктор
    public Game(Horse[] horses, int lapCount) {
        this.horses = horses;
        this.lapCount = lapCount;
    }
    //процесс игры
    public void process(){
        System.out.println("Скачки начались...");
        //массив для хранения времени забега каждой лошади
        int[] totalTime = new int[horses.length];
        //начинаем забег
        for (int i=1; i<=lapCount; i++){
            System.out.println("начался круг №" + i);
            //цикл обхода каждой лошади
            for (int k=0; k<horses.length;k++){
                if (horses[k].isFail) totalTime[k] = -1;
                else totalTime[k] += horses[k].getLapTime();
            }
            System.out.println("текущие результаты:");
            System.out.println(Arrays.toString(totalTime));
            System.out.println("Текущий лидер: " + getWinHorse(totalTime).getName());
            if (getCountGamers(totalTime) == 1) break;

        }
        System.out.println(Arrays.toString(totalTime));
        System.out.println("Победитель скачек " + getWinHorse(totalTime).getName());
    }
    //метод, возвращающий лошадь-лидера
    public Horse getWinHorse(int[] totalTime){
        int min = totalTime[0];
        int index = 0;
        for (int i=1; i< totalTime.length; i++){
            if (totalTime[i]<min){
                min = totalTime[i];
                index = i;
            }
        }
        return horses[index];
    }
    public int getCountGamers(int[] totalTime){
        int count = totalTime.length;
        for (int i=0; i< totalTime.length; i++){
            if (totalTime[i] == -1) count--;
        }
        return count;
    }
}