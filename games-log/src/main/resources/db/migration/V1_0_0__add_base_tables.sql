create table if not exists "user"
(
    id   uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name varchar
);

create table if not exists game
(
    id   uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name varchar
);

create table if not exists user_game
(
    id uuid DEFAULT gen_random_uuid(),
    user_id uuid,
    game_id uuid,
    label varchar,
    PRIMARY KEY (user_id, game_id, label),
    FOREIGN KEY(user_id) REFERENCES "user"(id),
    FOREIGN KEY (game_id) REFERENCES  game(id)
)