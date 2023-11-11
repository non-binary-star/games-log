package md.usm.fmi.gameslog.model.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HttpConstant {
    public static final String API_PATH = "/api";

    public static final String GAME = "/game";
    public static final String GAME_PATH = API_PATH + GAME;
    public static final String GAME_ID = "game_id";
    public static final String GAME_ID_PATH = "/{" + GAME_ID + "}";
    public static final String GET_GAME_PATH = GAME_PATH + GAME_ID_PATH;

    public static final String USER_PATH = API_PATH + "/user";
    public static final String USER_ID = "user_id";
    public static final String USER_ID_PATH = "/{" + USER_ID + "}";
    public static final String USER_GAME_PATH = USER_ID_PATH + GAME;
}
