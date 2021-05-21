package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;

import java.util.Comparator;
import java.util.Map;

public class GetPlayersRequest {
    private final String name;
    private final String title;
    private final Race race;
    private final Profession profession;
    private final Long after;
    private final Long before;
    private final Boolean banned;
    private final Integer minExperience;
    private final Integer maxExperience;
    private final Integer minLevel;
    private final Integer maxLevel;
    private final PlayerOrder order;
    private final Integer pageNumber;
    private final Integer pageSize;

    public GetPlayersRequest(Map<String, String> params) {
        name = params.getOrDefault("name", null);
        title = params.getOrDefault("title", null);
        race = (params.containsKey("race")) ? Race.valueOf(params.get("race")) : null;
        profession = (params.containsKey("profession")) ? Profession.valueOf(params.get("profession")) : null;
        after = (params.containsKey("after")) ? Long.parseLong(params.get("after")) : null;
        before = (params.containsKey("before")) ? Long.parseLong(params.get("before")) : null;
        banned = (params.containsKey("banned")) ? Boolean.parseBoolean(params.get("banned")) : null;
        minExperience = (params.containsKey("minExperience")) ? Integer.parseInt(params.get("minExperience")) : null;
        maxExperience = (params.containsKey("maxExperience")) ? Integer.parseInt(params.get("maxExperience")) : null;
        minLevel = (params.containsKey("minLevel")) ? Integer.parseInt(params.get("minLevel")) : null;
        maxLevel = (params.containsKey("maxLevel")) ? Integer.parseInt(params.get("maxLevel")) : null;
        order = (params.containsKey("order")) ? PlayerOrder.valueOf(params.get("order")) : PlayerOrder.ID;
        pageNumber = (params.containsKey("pageNumber")) ? Integer.parseInt(params.get("pageNumber")) : 0;
        pageSize = (params.containsKey("pageSize")) ? Integer.parseInt(params.get("pageSize")) : 3;
    }

    public boolean predicateName(Player player) {
        return name == null || player.getName().contains(name);
    }

    public boolean predicateTitle(Player player) {
        return title == null || player.getTitle().contains(title);
    }

    public boolean predicateRace(Player player) {
        return race == null || player.getRace().equals(race);
    }

    public boolean predicateProfession(Player player) {
        return  profession == null || player.getProfession().equals(profession);
    }

    public boolean predicateBirthday(Player player) {
        return (after == null || after <= player.getBirthday().getTime() ) &&
               (before == null || player.getBirthday().getTime() <= before);
    }

    public boolean predicateBanned(Player player) {
        return banned == null || player.getBanned().equals(banned);
    }

    public boolean predicateExperience(Player player) {
        return (minExperience == null || player.getExperience() >= minExperience) &&
               (maxExperience == null || player.getExperience() <= maxExperience);
    }

    public boolean predicateLevel(Player player) {
        return (minLevel == null || player.getLevel() >= minLevel) &&
               (maxLevel == null || player.getLevel() <= maxLevel);
    }


    public Comparator<Player> getComparator() throws Exception {
        switch (order) {
            case ID:
                return Comparator.comparing(Player::getId);
            case NAME:
                return Comparator.comparing(Player::getName);
            case LEVEL:
                return Comparator.comparing(Player::getLevel);
            case BIRTHDAY:
                return Comparator.comparing(Player::getBirthday);
            case EXPERIENCE:
                return Comparator.comparing(Player::getExperience);
        }
        throw new Exception("[Error] GetPlayersRequest.getComparator");
    }

    public long skip() {
        return (long) pageSize * pageNumber;
    }

    public long limit() {
        return pageSize;
    }
}
