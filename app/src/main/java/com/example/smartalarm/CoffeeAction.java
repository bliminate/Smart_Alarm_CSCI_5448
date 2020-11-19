package com.example.smartalarm;

import java.io.Serializable;

class CoffeeAction extends Action implements Serializable {
    public CoffeeAction(String name, int waterAmount, int groundAmount) {
        type = "CoffeeAction";
        this.name = name;
        this.waterAmount = waterAmount;
        this.groundAmount = groundAmount;
    }

    public String name;
    public String type;
    public int waterAmount;
    public int groundAmount;
}


