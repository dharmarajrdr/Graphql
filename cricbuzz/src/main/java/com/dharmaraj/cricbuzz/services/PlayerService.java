package com.dharmaraj.cricbuzz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.dharmaraj.cricbuzz.models.Player;
import com.dharmaraj.cricbuzz.models.Role;
import com.dharmaraj.cricbuzz.models.Team;

import jakarta.annotation.PostConstruct;

@Service
public class PlayerService {

    private final List<Player> players = new ArrayList<>();

    private final AtomicInteger idCounter = new AtomicInteger(0);

    public List<Player> findAll() {

        return players;
    }

    public Optional<Player> findById(Integer id) {

        return players.stream().filter(player -> player.id().equals(id)).findFirst();
    }

    public Player save(String name, Role role, Team team) {

        Player newPlayer = new Player(idCounter.incrementAndGet(), name, role, team);
        players.add(newPlayer);
        return newPlayer;
    }

    public Player deleteById(Integer id) {

        Player player = findById(id).orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
        players.remove(player);
        return player;
    }

    public Player update(Integer id, String name, Role role, Team team) {

        Player player = findById(id).orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
        int index = players.indexOf(player);
        players.set(index, new Player(id, name, role, team));
        return players.get(index);
    }

    /**
     * Saves some initial data to the players list on application startup.
     * This method is annotated with @PostConstruct, which means it will be executed
     * after the bean's properties have been set.
     */
    @PostConstruct
    public void seedData() {
        save("Virat Kohli", Role.BATSMAN, Team.RCB);
        save("MS Dhoni", Role.WICKETKEEPER, Team.CSK);
        save("Sanju Samson", Role.BATSMAN, Team.RR);
        save("Bhuwaneshwar Kumar", Role.BOWLER, Team.SRH);
        save("Hardik Pandya", Role.ALLROUNDER, Team.MI);
        save("Rishabh Pant", Role.WICKETKEEPER, Team.DC);
    }
}
