package md.usm.fmi.gameslog.domain.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import md.usm.fmi.gameslog.infrastructure.outbound.db.user.UserRepository;
import md.usm.fmi.gameslog.model.dto.User;
import md.usm.fmi.gameslog.model.entity.EUser;
import md.usm.fmi.gameslog.model.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return User.of(userRepository.findAll());
    }

    public User findById(final UUID id) {
        return userRepository.findById(id)
                .map(User::of)
                .orElse(null);
    }

    public User findByName(final String name) {
        return userRepository.findByName(name)
                .map(User::of)
                .orElse(null);
    }

    @Transactional
    public UUID create(final User user) {
        return userRepository.save(EUser.of(user));
    }

    @Transactional
    public void update(final UUID id, final User user) {
        userRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userRepository.save(EUser.of(id, user));
    }

    @Transactional
    public void deleteById(final UUID id) {
        final EUser foundUser = userRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userRepository.delete(foundUser);
    }
}
