package com.game.dto;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;

public class PlayerDTO {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long birthday;
    private Boolean banned;
    private Integer experience;

    public Player update(Player player) {
        if (name == null || name.equals("") || name.length() > 12) return null;




        player.setName(name);

        return player;
    }
}
