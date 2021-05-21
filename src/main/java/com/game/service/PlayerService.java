package com.game.service;

import com.game.controller.GetPlayersRequest;
import com.game.entity.Player;

import java.util.List;
import java.util.Map;

public interface PlayerService {
    Player create(Map<String, String> params) throws Exception;
    Player findById(Long id);
    void delete(Player player);
    Player update(Player player);
    List<Player> getPlayers(GetPlayersRequest request) throws Exception;
    Integer getCount(GetPlayersRequest request);
}
