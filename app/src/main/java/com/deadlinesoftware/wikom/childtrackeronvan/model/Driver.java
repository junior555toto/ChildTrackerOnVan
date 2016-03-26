package com.deadlinesoftware.wikom.childtrackeronvan.model;

/**
 * Created by junio on 12/3/2559.
 */
public class Driver {
    public final int id;
    public final String name;
    public final int gender;
    public final String citizenId;
    public final String uuid;
    public final String avatar;

    public Driver(int id, String name, int gender, String citizenId, String uuid, String avatar) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.citizenId = citizenId;
        this.uuid = uuid;
        this.avatar = avatar;
    }
}
