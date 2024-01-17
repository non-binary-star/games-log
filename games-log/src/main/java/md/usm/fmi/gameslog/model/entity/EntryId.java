package md.usm.fmi.gameslog.model.entity;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class EntryId implements Serializable {
    private UUID userId;
    private UUID gameId;
    private String label;
}
