package md.usm.fmi.gameslog.infrastructure.outbound.db.user;

import md.usm.fmi.gameslog.model.entity.EUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    List<EUser> findAll();

    Optional<EUser> findById(UUID id);

    Optional<EUser> findByName(String name);

    boolean existsById(UUID id);

    UUID save(EUser entity);

    void delete(EUser entity);
}
