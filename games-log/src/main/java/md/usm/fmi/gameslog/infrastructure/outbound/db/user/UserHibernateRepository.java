package md.usm.fmi.gameslog.infrastructure.outbound.db.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import md.usm.fmi.gameslog.model.entity.EUser;
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
public class UserHibernateRepository implements UserRepository {
    private final EntityManager entityManager;

    @Override
    public List<EUser> findAll() {
        return entityManager.createQuery("select u from EUser u", EUser.class).getResultList();
    }

    @Override
    public Optional<EUser> findById(final UUID id) {
        return ofNullable(entityManager.find(EUser.class, id));
    }

    @Override
    public Optional<EUser> findByName(final String name) {
        return entityManager.createQuery("select u from EUser u where u.name = :name", EUser.class)
                .setParameter("name", name)
                .getResultList().stream()
                .findFirst();
    }

    @Override
    public boolean existsById(final UUID id) {
        return nonNull(entityManager.getReference(EUser.class, id));
    }

    @Override
    public UUID save(final EUser entity) {
        if (nonNull(entity.getId())) {
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
        return entity.getId();
    }

    @Override
    public void delete(final EUser entity) {
        entityManager.remove(entity);
    }
}
