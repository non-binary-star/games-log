package md.usm.fmi.gameslog.model.dto;

import md.usm.fmi.gameslog.model.entity.EEntry;

import java.util.List;

public record Entry(Game game, String label) {

    public static Entry of(final EEntry entity) {
        return new Entry(Game.of(entity.getGame()), entity.getLabel());
    }

    public static List<Entry> of(final List<EEntry> entities) {
        return entities.stream()
                .map(Entry::of)
                .toList();
    }
}
