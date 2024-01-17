package md.usm.fmi.gameslog.infrastructure.outbound.db.game;

import md.usm.fmi.gameslog.model.entity.EGame;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GamesRepository {
    List<EGame> findAll();

    Optional<EGame> findById(UUID id);

    boolean existsById(UUID id);

    UUID save(EGame entity);

    void delete(EGame entity);
}
