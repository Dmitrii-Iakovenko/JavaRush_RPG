package com.game.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 12)
    private String name;

    @Column(length = 30)
    private String title;
    private Race race;
    private Profession profession;
    private Date birthday;
    private Boolean banned;
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;

    protected Player() {

    }

    public Player(String name, String title, Race race, Profession profession, Date birthday, Boolean banned, Integer experience) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = banned;
        this.experience = experience;

        level = (int) ((Math.sqrt(2500 + 200 * experience) - 50) / 100);
        untilNextLevel = 50 * (level + 1) * (level + 2) - experience;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }
}
