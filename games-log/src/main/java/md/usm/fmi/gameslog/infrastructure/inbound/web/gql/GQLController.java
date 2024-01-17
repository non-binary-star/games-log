package md.usm.fmi.gameslog.infrastructure.inbound.web.gql;

import lombok.RequiredArgsConstructor;
import md.usm.fmi.gameslog.domain.gamelist.GameListService;
import md.usm.fmi.gameslog.domain.user.UserService;
import md.usm.fmi.gameslog.model.dto.Entry;
import md.usm.fmi.gameslog.model.dto.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class GQLController {

    private final UserService userService;
    private final GameListService gameListService;

    @QueryMapping
    public User userById(@Argument final UUID id) {
        return userService.findById(id);
    }

    @QueryMapping
    public User userByName(@Argument final String name) {
        return userService.findByName(name);
    }

    @QueryMapping
    public List<Entry> gamesListByUserId(@Argument final UUID userId) {
        return gameListService.getUserGames(userId);
    }
}