package com.example;

import java.util.List;

public interface IFeline {
    int getKittens();
    boolean doesHaveMane();

    default List<String> getFood(String хищник) throws Exception {
        return null;
    }
}
