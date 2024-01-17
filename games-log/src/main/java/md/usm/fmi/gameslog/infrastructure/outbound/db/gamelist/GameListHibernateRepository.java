package md.usm.fmi.gameslog.infrastructure.outbound.db.gamelist;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import md.usm.fmi.gameslog.infrastructure.outbound.db.game.GamesRepository;
import md.usm.fmi.gameslog.model.entity.EGame;
import md.usm.fmi.gameslog.model.entity.EEntry;
import md.usm.fmi.gameslog.model.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class GameListHibernateRepository implements GameListRepository {

    private final EntityManager entityManager;

    @Override
    public List<EEntry> getByUserId(final UUID userId) {
        return entityManager.createQuery("""
                       select g from EUser u join u.games g where u.id = :user_id""", EEntry.class)
                .setParameter("user_id", userId)
                .getResultList();
    }

    @Override
    public void saveForUser(final UUID userId, final UUID gameId, final String state) {
        final EEntry eEntry = new EEntry(userId, gameId, state);
        entityManager.persist(eEntry);
    }

    @Override
    public void updateForUser(final UUID userId, final EGame game) {

    }

    @Override
    public void deleteForUser(final UUID uuid) {

    }
}
