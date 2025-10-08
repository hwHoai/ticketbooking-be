package com.example.mvc_spring_postgres.entity.event;

import com.example.mvc_spring_postgres.entity.relation.EventTagRelation;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="event_001", indexes = {
        @Index(name = "idx_tittle_address", columnList = "tittle, address"),
})
@EnableJpaRepositories
@DynamicUpdate
@DynamicInsert
public class EventEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "tittle", columnDefinition = "VARCHAR(255)", nullable = false)
    private String tittle;

    @Column(name = "category", columnDefinition = "VARCHAR(255)", nullable = false)
    private String category;

    @Column(name = "thumbnail", columnDefinition = "VARCHAR(255)", nullable = false)
    private String thumbnail;

    @Column(name = "address", columnDefinition = "VARCHAR(255)", nullable = false)
    private String address;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN", nullable = false)
    private boolean isDeleted;

//    Relationship
//    One to One
    @OneToOne(mappedBy = "event", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private EventDetailEntity eventDetail;

    @OneToOne(mappedBy = "event", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private EventStoreEntity eventStore;
//    One to Many
    @OneToMany(mappedBy = "eventId", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private List<EventTagRelation> eventTagRelations;
//    Many to One
//    Many to Many
}
