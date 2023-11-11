package md.usm.fmi.gameslog.model.dto;

import md.usm.fmi.gameslog.model.entity.EGame;

import java.util.List;
import java.util.UUID;

public record Game(UUID id, String name) {

    public static Game of(final EGame entity) {
        return new Game(entity.getId(), entity.getName());
    }

    public static List<Game> of(final List<EGame> entities) {
        return entities.stream()
                .map(Game::of)
                .toList();
    }
}
