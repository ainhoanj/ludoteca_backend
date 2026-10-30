package com.ccsw.tutorial1.game;

import com.ccsw.tutorial1.game.model.Game;
import com.ccsw.tutorial1.game.model.GameDto;

import java.util.List;

public interface GameService {


    List<Game> find(String title, Long idCategory);


    void save(Long id, GameDto dto);

}
