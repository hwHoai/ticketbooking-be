package com.my_project.ticket_booking.entity.event;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Data
@Table(name = "event_detail_001", indexes = {
        @Index(name = "idx_eventId", columnList = "event_id"),
        @Index(name = "idx_organizer_startDate", columnList = "organizer, start_date"),
})
@Entity
@EnableJpaRepositories
@DynamicUpdate
@DynamicInsert
public class EventDetailEntity {
    @Id
    @OneToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", columnDefinition = "UUID", nullable = false)
    private EventEntity event;

    @Column(name="organizer", columnDefinition = "VARCHAR(255)", nullable = false)
    private String organizer;

    @Column(name="description", columnDefinition = "VARCHAR(255)", nullable = false)
    private String description;

    @Column(name="map", columnDefinition = "json", nullable = false)
    @Type(JsonType.class)
    private String map;

    @Column(name="banner", columnDefinition = "VARCHAR(255)", nullable = false)
    private String banner;

    @Column(name="start_date", columnDefinition = "TIMESTAMP", nullable = false)
    private String startDate;

    @Column(name="end_date", columnDefinition = "TIMESTAMP", nullable = false)
    private String endDate;

    @Column(name="created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private String createdAt;
}
