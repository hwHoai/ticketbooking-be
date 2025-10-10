package com.my_project.ticket_booking.entity.ticket.ticket_type;

import com.my_project.ticket_booking.entity.ticket.TicketEntiry;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Data
@Entity
@Table(name="concert_ticket_001",
indexes = {
        @Index(name = "idx_ticketId", columnList = "ticket_id"),
        @Index(name = "idx_type_zone_chair", columnList = "type, zone, chair_num"),
})
@EnableJpaRepositories
@DynamicUpdate
@DynamicInsert
public class ConcertTicket {
    @Id
    @OneToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "id", columnDefinition = "UUID", nullable = false)
    private TicketEntiry ticketId;

    @Column(name = "type", columnDefinition = "VARCHAR(255)", nullable = false)
    private String type;

    @Column(name = "zone", columnDefinition = "VARCHAR(255)", nullable = false)
    private String zone;

    @Column(name = "chair_num", columnDefinition = "VARCHAR(255)", nullable = false)
    private String chairNum;

}
