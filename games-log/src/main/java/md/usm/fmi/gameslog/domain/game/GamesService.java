package md.usm.fmi.gameslog.domain.game;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import md.usm.fmi.gameslog.infrastructure.outbound.db.game.GamesRepository;
import md.usm.fmi.gameslog.model.dto.Game;
import md.usm.fmi.gameslog.model.entity.EGame;
import md.usm.fmi.gameslog.model.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GamesService {

    private final GamesRepository gamesRepository;

    public List<Game> findAll() {
        return Game.of(gamesRepository.findAll());
    }

    public Game findById(final UUID id) {
        return gamesRepository.findById(id)
                .map(Game::of)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public UUID create(final Game game) {
        return gamesRepository.save(EGame.of(game));
    }

    @Transactional
    public void update(final UUID id, final Game game) {
        gamesRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        gamesRepository.save(EGame.of(id, game));
    }

    @Transactional
    public void deleteById(final UUID id) {
        final EGame foundGame = gamesRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        gamesRepository.delete(foundGame);
    }
}
