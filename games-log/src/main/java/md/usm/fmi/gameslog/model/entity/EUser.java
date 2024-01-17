package md.usm.fmi.gameslog.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.usm.fmi.gameslog.model.dto.User;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"user\"")
@NoArgsConstructor
public class EUser {

    @Id
    private UUID id;

    private String name;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<EEntry> games;

    public EUser(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static EUser of(final User user) {
        return new EUser(user.id(), user.name());
    }

    public static EUser of(final UUID id, final User user) {
        return new EUser(id, user.name());
    }
}
