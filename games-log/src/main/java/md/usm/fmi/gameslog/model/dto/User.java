package md.usm.fmi.gameslog.model.dto;

import md.usm.fmi.gameslog.model.entity.EUser;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public record User(UUID id, String name, List<Entry> games) {

    public static User of(final EUser entity) {
        return new User(entity.getId(), entity.getName(), Collections.emptyList());
    }

    public static List<User> of(final List<EUser> entities) {
        return entities.stream()
                .map(User::of)
                .toList();
    }
}
