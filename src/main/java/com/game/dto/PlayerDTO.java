package com.game.dto;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;

import java.awt.image.DataBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PlayerDTO {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long birthday;
    private Boolean banned;
    private Integer experience;

    public Player update(Player player) throws Exception {

        if (name != null) {
            if (name.equals("") || name.length() > 12) throw new Exception("Bad request[name]");
            player.setName(name);
        }

        if (title != null) {
            if (title.length() > 30) throw new Exception("Bad request[title]");
            player.setTitle(title);
        }

        if (race != null) {
            player.setRace(race);
        }

        if (profession != null) {
            player.setProfession(profession);
        }

        if (birthday != null) {
            if (birthday < 0) throw new Exception("Bad request[birthday]");
            Date birthdayDate = new Date(birthday);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(birthdayDate);
            int year = calendar.get(Calendar.YEAR);
            if (year < 2000 || year > 3000) throw new Exception("Bad request[birthday]");
            player.setBirthday(birthdayDate);
        }

        if (banned != null) {
            player.setBanned(banned);
        }

        if (experience != null) {
//            if (experience < 0 || experience > 10_000_000) throw new Exception("Bad request[experience]");
            player.setExperience(experience);
        }

        // все поля валидны, возвращаем того же игрока с изменёнными полями
        // не забыть его сохранить в базе в контроллере
        return player;
    }
}
