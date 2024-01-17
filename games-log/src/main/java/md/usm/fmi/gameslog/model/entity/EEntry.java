package md.usm.fmi.gameslog.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_game")
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
    private String label;

    public EEntry(final UUID userId, final UUID gameId, final String label) {
        this.userId = userId;
        this.gameId = gameId;
        this.label = label;
    }
}
