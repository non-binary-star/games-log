package md.usm.fmi.gameslog.infrastructure.inbound.web.controller;

import lombok.RequiredArgsConstructor;
import md.usm.fmi.gameslog.application.properties.ApplicationProperties;
import md.usm.fmi.gameslog.domain.game.GamesService;
import md.usm.fmi.gameslog.model.dto.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static md.usm.fmi.gameslog.model.constant.HttpConstant.*;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;

@CrossOrigin("${ui.app.origin}")
@RestController
@RequiredArgsConstructor
@RequestMapping(GAME_PATH)
public class GameController {

    private final GamesService gamesService;

    private final ApplicationProperties properties;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ok(gamesService.findAll());
    }

    @GetMapping(GAME_ID_PATH)
    public ResponseEntity<Game> getGame(@PathVariable(GAME_ID) final UUID id) {
        return ok(gamesService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody final Game game) {
        final UUID createGameId = gamesService.create(game);
        final URI resourceUri = fromUri(properties.getHost())
                .path(GET_GAME_PATH)
                .build(createGameId);
        return created(resourceUri).build();
    }

    @PatchMapping(GAME_ID_PATH)
    public ResponseEntity<Void> updateGame(@PathVariable(GAME_ID) final UUID id, @RequestBody final Game body) {
        gamesService.update(id, body);
        return noContent().build();
    }

    @DeleteMapping(GAME_ID_PATH)
    public ResponseEntity<Game> deleteGame(@PathVariable(GAME_ID) final UUID id) {
        gamesService.deleteById(id);
        return noContent().build();
    }
}
