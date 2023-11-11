package md.usm.fmi.gameslog.infrastructure.outbound.db.game;

import md.usm.fmi.gameslog.model.entity.EGame;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(value = "application.persistence.implementation", havingValue = "SDATA")
public interface GamesJpaRepository extends GamesRepository {

    @Override
    List<EGame> findAll();
}
