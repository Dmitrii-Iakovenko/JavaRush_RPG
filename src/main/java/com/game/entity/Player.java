package com.game.entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;


@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 12)
    private String name;
    @Column(length = 30)
    private String title;
    @Enumerated(EnumType.STRING)
    private Race race;
    @Enumerated(EnumType.STRING)
    private Profession profession;
    private Date birthday;
    private Boolean banned;
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;


    protected Player() {

    }

    public Player(String name, String title, Race race, Profession profession, Date birthday, Boolean banned, Integer experience) throws Exception {
        setName(name);
        setTitle(title);
        this.race = race;
        this.profession = profession;
        setBirthday(birthday);
        setBanned(banned);
        setExperience(experience);
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

    public void setProfession(String profession) {
        this.profession = Profession.valueOf(profession);
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


    public void setName(String name) throws Exception {
        if (name == null || name.equals("") || name.length() > 12)
            throw new Exception("[Error] Player.setName");
        this.name = name;
    }

    public void setTitle(String title) throws Exception {
        if (title == null || title.length() > 30)
            throw new Exception("[Error] Player.setTitle");
        this.title = title;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setBirthday(Date birthday) throws Exception {
        if (birthday == null)
            throw new Exception("[Error] Player.setBirthday");

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(birthday);
        int year = calendar.get(Calendar.YEAR);
        if (year < 2000 || year > 3000)
            throw new Exception("[Error] Player.setBirthday");

        this.birthday = birthday;
    }

    public void setBanned(Boolean banned) {
        this.banned = (banned != null) ? banned : false;
    }

    public void setExperience(Integer experience) throws Exception {
        if (experience < 0 || experience > 10_000_000)
            throw new Exception("[Error] Player.setExperience");
        this.experience = experience;
        level = (int) ((Math.sqrt(2500 + 200 * experience) - 50) / 100);
        untilNextLevel = 50 * (level + 1) * (level + 2) - experience;
    }


}
