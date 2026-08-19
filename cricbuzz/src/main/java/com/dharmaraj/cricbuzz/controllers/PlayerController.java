package com.dharmaraj.cricbuzz.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.dharmaraj.cricbuzz.models.Player;
import com.dharmaraj.cricbuzz.models.Role;
import com.dharmaraj.cricbuzz.models.Team;
import com.dharmaraj.cricbuzz.services.PlayerService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @QueryMapping
    public List<Player> findAlls() { // Same name as defined in schema.graphqls file

        return playerService.findAll();
    }

    @QueryMapping
    public List<Player> findAllByTeam(@Argument Team team) { // Same name as defined in schema.graphqls file

        return playerService.findAll().stream().filter(player -> player.team().name().equalsIgnoreCase(team.name()))
                .toList();
    }

    @MutationMapping
    public Player save(@Argument String name, @Argument Role role, @Argument Team team) {

        return playerService.save(name, role, team);
    }
}
