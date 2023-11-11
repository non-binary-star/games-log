package md.usm.fmi.gameslog.infrastructure.outbound.db.game;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import md.usm.fmi.gameslog.model.entity.EGame;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(value = "application.persistence.implementation", havingValue = "HIBERNATE")
public class GamesHibernateRepository implements GamesRepository {

    private final EntityManager entityManager;

    @Override
    public List<EGame> findAll() {
        return entityManager.createQuery("select g from EGame g", EGame.class).getResultList();
    }

    @Override
    public Optional<EGame> findById(final UUID id) {
        return ofNullable(entityManager.find(EGame.class, id));
    }

    @Override
    public boolean existsById(final UUID id) {
        return nonNull(entityManager.getReference(EGame.class, id));
    }

    @Override
    public UUID save(final EGame entity) {
        if (nonNull(entity.getId())) {
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
        return entity.getId();
    }

    @Override
    public void delete(final EGame entity) {
        entityManager.remove(entity);
    }
}
