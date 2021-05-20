package com.game.controller;


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
        Player player = playerService.create(params);
        return (player != null)
                ? new ResponseEntity<>(player, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping("{id}")
    public ResponseEntity<Player> get(@PathVariable Long id) {
        // не валидное ID
        if (id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Player player = playerService.findById(id);
        return (player != null)
                ? new ResponseEntity<>(player, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
