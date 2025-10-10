package com.my_project.ticket_booking.entity.event;

import com.my_project.ticket_booking.entity.ticket.TicketEntiry;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@Data
@Table(name = "event_store_001", indexes = {
        @Index(name = "idx_id", columnList = "id"),
})
@Entity
@EnableJpaRepositories
@DynamicInsert
@DynamicUpdate
public class EventStoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "UUID", updatable = false, nullable = false)
    private String id;

//    Relationship
//    One to One
    @OneToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", columnDefinition = "UUID", nullable = false)
    private EventEntity event;

    @OneToMany(mappedBy = "eventStore", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)

    private List<TicketEntiry> ticketList;
}
