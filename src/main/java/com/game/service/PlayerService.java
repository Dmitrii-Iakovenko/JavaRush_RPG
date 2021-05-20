package com.game.service;

import com.game.entity.Player;

import java.util.Map;

public interface PlayerService {
    Player create(Map<String, String> params);
}
