package md.usm.fmi.gameslog.infrastructure.inbound.web.controller;

import lombok.RequiredArgsConstructor;
import md.usm.fmi.gameslog.domain.gamelist.GameListService;
import md.usm.fmi.gameslog.model.dto.Entry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static md.usm.fmi.gameslog.model.constant.HttpConstant.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin("${ui.app.origin}")
@RestController
@RequestMapping(USER_PATH)
@RequiredArgsConstructor
public class GameListController {

    private final GameListService gameListService;

    @GetMapping(USER_GAME_PATH)
    public ResponseEntity<List<Entry>> getUserGames(@PathVariable(USER_ID) final UUID userId) {
        return ok(gameListService.getUserGames(userId));
    }

    @PostMapping(USER_GAME_PATH + GAME_ID_PATH)
    public ResponseEntity<Void> saveForUser(@PathVariable(USER_ID) final UUID userId,
                                            @PathVariable(GAME_ID) final UUID gameId,
                                            @RequestBody final Entry requestBody) {
        gameListService.saveGameForUser(userId, gameId, requestBody.state());
        return ResponseEntity.status(CREATED).build();
    }
}
