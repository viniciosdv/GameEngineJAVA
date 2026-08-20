package com.engine.core.components;

public class StatsComponent implements Component {
    public String name;
    public int attackPower;

    public StatsComponent(String name, int attackPower) {
        this.name = name;
        this.attackPower = attackPower;
    }
}