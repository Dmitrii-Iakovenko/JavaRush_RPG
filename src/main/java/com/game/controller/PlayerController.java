package com.game.controller;


import com.game.dto.PlayerDTO;
import com.game.entity.Player;
import com.game.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/players")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @PostMapping("")
//    @ResponseBody
    public ResponseEntity<Player> create(@RequestBody Map<String, String> params) {
        try {
            Player player = playerService.create(params);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("{idString}")
    public ResponseEntity<Player> get(@PathVariable String idString) {
        long id;

        // не валидное ID
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        // id валидное. ищем игрока
        Player player = playerService.findById(id);
        return (player != null)
                ? new ResponseEntity<>(player, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("{idString}")
    public ResponseEntity<Void> delete(@PathVariable String idString) {
        long id;

        // не валидное ID
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        // id валидное. ищем игрока
        Player player = playerService.findById(id);
        if (player != null) {
            playerService.delete(player);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{id}")
    public ResponseEntity<Player> update(@PathVariable Long id,
                                         @RequestBody PlayerDTO playerDTO) {
        // не валидное ID
        if (id <= 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        // id валидное. но такого игрока нет
        Player player = playerService.findById(id);
        if (player == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // игрок найдён
        try {
            player = playerDTO.update(player);
            playerService.update(player);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
















