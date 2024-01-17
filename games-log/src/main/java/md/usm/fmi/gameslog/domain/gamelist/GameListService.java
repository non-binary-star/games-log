package md.usm.fmi.gameslog.domain.gamelist;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import md.usm.fmi.gameslog.infrastructure.outbound.db.gamelist.GameListRepository;
import md.usm.fmi.gameslog.model.dto.Entry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameListService {
    private final GameListRepository gameListRepository;

    public List<Entry> getUserGames(final UUID userId) {
        return Entry.of(gameListRepository.getByUserId(userId));
    }

    @Transactional
    public void saveGameForUser(final UUID userId, final UUID gameId, final String state) {
        gameListRepository.saveForUser(userId, gameId, state);
    }
}
