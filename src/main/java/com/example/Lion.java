package com.example;

import java.util.List;

public class Lion {

    boolean hasMane;
    IFeline feline;

    public Lion(String sex, IFeline feline) throws Exception {
        this.feline = new IFeline() {
            @Override
            public int getKittens() {
                return 0;
            }

            @Override
            public boolean doesHaveMane() {
                return false;
            }
        };

        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else throw new Exception("Используйте допустимые значения пола животного - самец или самка");
    }

    public Lion(String самец, Feline felineMock) {
        feline = new IFeline() {
            @Override
            public int getKittens() {
                return 0;
            }

            @Override
            public boolean doesHaveMane() {
                return false;
            }
        };
    }

    public int getKittens() {
        return feline.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return feline.getFood("Хищник");
    }
}
