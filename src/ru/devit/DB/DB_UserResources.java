package ru.devit.DB;

/**
 * Created by user on 23.04.2015.
 */
public class DB_UserResources {
    private int user_id;
    private Float gold;
    private Float grain;
    private Float wood;
    private Float peasant;
    private Float soldiers;
    private Float money;
    private Float mood;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Float getGold() {
        return gold;
    }

    public void setGold(Float gold) {
        this.gold = gold;
    }

    public Float getGrain() {
        return grain;
    }

    public void setGrain(Float grain) {
        this.grain = grain;
    }

    public Float getWood() {
        return wood;
    }

    public void setWood(Float wood) {
        this.wood = wood;
    }

    public Float getPeasant() {
        return peasant;
    }

    public void setPeasant(Float peasant) {
        this.peasant = peasant;
    }

    public Float getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(Float soldiers) {
        this.soldiers = soldiers;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Float getMood() {
        return mood;
    }

    public void setMood(Float mood) {
        this.mood = mood;
    }

    @Override
    public String toString()
    {
        String s = "user_id: " + user_id + "\n";
        s += "gold: " + gold + "\n";
        s += "grain: " + grain + "\n";
        s += "wood: " + wood + "\n";
        s += "peasant: " + peasant + "\n";
        s += "soldiers: " + soldiers + "\n";
        s += "money: " + money + "\n";
        return s;
    }
}
