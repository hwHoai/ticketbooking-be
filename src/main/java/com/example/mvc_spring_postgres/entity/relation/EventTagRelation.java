package com.example.mvc_spring_postgres.entity.relation;

import com.example.mvc_spring_postgres.entity.event.EventEntity;
import com.example.mvc_spring_postgres.entity.event.EventTagNameEntity;
import com.example.mvc_spring_postgres.entity.relation.composite_key.EventTagRelationKey;
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
