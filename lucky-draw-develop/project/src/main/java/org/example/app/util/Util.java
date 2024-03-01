package org.example.app.util;

import org.example.app.constant.Prize;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.*;

public class Util {
    public static Integer getRandomInteger(int minRange, int maxRange){
        return RandomGenerator.getRandomInt(minRange, maxRange);
    }

    public static String getRandomString(){
        return UUID.randomUUID().toString();
    }

    public static String getRandomStringInList(ArrayList<String> listString){
        Random random = new Random();
        int randomIndex = random.nextInt(listString.size());
        return listString.get(randomIndex);
    }

    public static Prize getRandomPrize(List<Prize> prizes){
        Random random = new Random();
        int randomIndex = random.nextInt(prizes.size());
        return prizes.get(randomIndex);
    }

    public static void sleep(Integer timeSleep){
        try {
            Thread.sleep(timeSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static  ArrayList<String> convertSettoArrayList(Set<String> set){
        return new ArrayList<>(set);
    }

    public static boolean isNull(Object object ){
        return object == null;
    }

    public static boolean isNotNull(Object object ){
        return !isNull(object);
    }

    public static long convertTimeToSecond(LocalTime localTime){
        long hour = localTime.getHour();
        long minute = localTime.getMinute();
        long second = localTime.getSecond();

        return hour*3600 + minute*60 + second;
    }

    public static boolean isNotContainCapitalLetter(String s){
        if(isNull(s)){
            return true;
        }
        String sLower = s.toLowerCase();
        return s.equals(sLower);
    }

    public static boolean isPositive(Integer number){
        if(isNull(number)){
            return false;
        }
        return number > 0;
    }

    public static boolean isNotPositive(int number){
        return !isPositive(number);
    }

    public static boolean objectsIsEqual(Object object1, Object object2){
        return object1 == object2;
    }

    public static boolean isTokenFieldExist(Object object){
        Class<?> clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField("token");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

}
