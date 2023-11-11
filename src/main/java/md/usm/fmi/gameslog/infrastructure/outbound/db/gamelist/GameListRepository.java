package md.usm.fmi.gameslog.infrastructure.outbound.db.gamelist;

import md.usm.fmi.gameslog.model.entity.EGame;
import md.usm.fmi.gameslog.model.entity.EEntry;

import java.util.List;
import java.util.UUID;

public interface GameListRepository {

    List<EEntry> getByUserId(UUID userId);

    void saveForUser(final UUID userId, final UUID gameId, final String state);

    void updateForUser(UUID userId, EGame game);

    void deleteForUser(UUID uuid);
}
