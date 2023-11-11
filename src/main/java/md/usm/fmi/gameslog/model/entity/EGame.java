package md.usm.fmi.gameslog.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.usm.fmi.gameslog.model.dto.Game;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "game")
public class EGame {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    public static EGame of(final Game dto) {
        return new EGame(dto.id(), dto.name());
    }

    public static EGame of(final UUID id, final Game dto) {
        return new EGame(id, dto.name());
    }
}
