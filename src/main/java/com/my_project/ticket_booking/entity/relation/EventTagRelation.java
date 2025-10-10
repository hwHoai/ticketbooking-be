package com.my_project.ticket_booking.entity.relation;

import com.my_project.ticket_booking.entity.event.EventEntity;
import com.my_project.ticket_booking.entity.event.EventTagNameEntity;
import com.my_project.ticket_booking.entity.relation.composite_key.EventTagRelationKey;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Entity
@Data
@Table(name="event_tag_relation_001")
@EnableJpaRepositories
@DynamicUpdate
@DynamicInsert
public class EventTagRelation {

    @EmbeddedId
    private EventTagRelationKey id;

    @ManyToOne(cascade = CascadeType.DETACH, targetEntity = EventEntity.class)
    @MapsId("event_id")
    @JoinColumn(name = "event_id", referencedColumnName = "id", columnDefinition = "UUID", nullable = false)
    private EventEntity eventId;

    @ManyToOne(cascade = CascadeType.DETACH, targetEntity = EventTagNameEntity.class)
    @MapsId("tag_id")
    @JoinColumn(name = "tag_id", referencedColumnName = "id", columnDefinition = "UUID", nullable = false)
    private EventTagNameEntity tagId;
}
