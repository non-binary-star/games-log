package md.usm.fmi.gameslog.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_games_list")
@IdClass(EntryId.class)
public class EEntry {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Id
    @Column(name = "game_id")
    private UUID gameId;

    @OneToOne
    @JoinColumn(name = "game_id", insertable = false)
    private EGame game;

    @Id
    private String state;

    public EEntry(final UUID userId, final UUID gameId, final String state) {
        this.userId = userId;
        this.gameId = gameId;
        this.state = state;
    }
}
