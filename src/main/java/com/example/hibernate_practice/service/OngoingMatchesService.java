package com.example.hibernate_practice.service;

import com.example.hibernate_practice.model.Match;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static final Map<String, Match> onGoingMatches = new ConcurrentHashMap<>();

    public static String addMatch(Match match) {
        String uuid = generateUniqueUUID();
        onGoingMatches.put(uuid, match);
        return uuid;
    }

    public static void addMatchNotUniqueUUID(Match match, String uuid) {
        onGoingMatches.put(uuid, match);
    }

    private static String generateUniqueUUID() {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (onGoingMatches.containsKey(uuid));
        return uuid;
    }

    public static Optional<Match> getMatch(String uuid) {
        return Optional.ofNullable(onGoingMatches.get(uuid));
    }

    public static void removeMatch(String uuid) {
        onGoingMatches.remove(uuid);
    }
}