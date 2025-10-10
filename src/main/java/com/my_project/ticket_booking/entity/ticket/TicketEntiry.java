package com.my_project.ticket_booking.entity.ticket;

import com.my_project.ticket_booking.entity.event.EventStoreEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@Data
@Entity
@Table(name="ticket_001", indexes = {
        @Index(name = "idx_tittle_price", columnList = "tittle, price"),
})
@EnableJpaRepositories
@DynamicUpdate
@DynamicInsert
public class TicketEntiry {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "purchaser", columnDefinition = "VARCHAR(255)", nullable = false)
    private String purchaser;

    @Column(name = "tittle", columnDefinition = "VARCHAR(255)", nullable = false)
    private String tittle;

    @Column(name = "description", columnDefinition = "VARCHAR(255)", nullable = false)
    private String description;

    @Column(name = "img", columnDefinition = "VARCHAR(255)", nullable = false)
    private String img;

    @Column(name = "policy", columnDefinition = "VARCHAR(255)", nullable = false)
    private String policy;

    @Column(name = "price", columnDefinition ="NUMERIC(10,2)", nullable = false)
    private String price;

    @Column(name ="is_deleted", columnDefinition = "BOOLEAN", nullable = false)
    private boolean isDeleted;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private String createdAt;


    //    Relationship
//    One to One
//    One to Many
//    Many to One
    @ManyToOne(cascade = CascadeType.DETACH, targetEntity = EventStoreEntity.class)
    @MapsId("store_id")
    @JoinColumn(name = "store_id", referencedColumnName = "id", columnDefinition = "UUID", nullable = false)
    private EventStoreEntity eventStore;
//    Many to Many
}
