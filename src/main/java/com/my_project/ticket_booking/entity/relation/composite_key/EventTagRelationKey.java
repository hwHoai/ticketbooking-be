package com.my_project.ticket_booking.entity.relation.composite_key;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

@Data
@Embeddable
public class EventTagRelationKey {
    private UUID event_id;
    private UUID tag_id;
}
