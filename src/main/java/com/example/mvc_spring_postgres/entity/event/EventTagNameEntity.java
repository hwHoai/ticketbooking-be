package com.example.mvc_spring_postgres.entity.event;

import com.example.mvc_spring_postgres.entity.relation.EventTagRelation;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.UUID;

@Data
@Table(name="event_tag_name_001")
@Entity
@EnableJpaRepositories
@DynamicUpdate
@DynamicInsert
public class EventTagNameEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "id", columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "tag_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String tagName;

//    Relationship
//    One to One
//    One to Many
    @OneToMany(mappedBy = "tagId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventTagRelation> eventTagRelations;
//    Many to One
//    Many to Many
}
