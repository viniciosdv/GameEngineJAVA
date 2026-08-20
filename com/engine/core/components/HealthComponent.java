package com.engine.core.components;

public class HealthComponent implements Component {
    public int currentHp;
    public int maxHp;

    public HealthComponent(int hp) {
        this.currentHp = hp;
        this.maxHp = hp;
    }
}