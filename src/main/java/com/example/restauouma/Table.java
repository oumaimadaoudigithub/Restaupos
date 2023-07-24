package com.example.restauouma;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private int tableNumber;
    private int numVisitors;
    private List<String> selectedCategories;

    public Table(int tableNumber){
        this.tableNumber = tableNumber;
        this.numVisitors = numVisitors;
        this.selectedCategories = new ArrayList<>();
    }
    public int getTableNumber(){
        return tableNumber;
    }
    public int getNumVisitors(){
        return numVisitors;
    }
}
