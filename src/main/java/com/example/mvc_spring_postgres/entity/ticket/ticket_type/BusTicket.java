package com.example.mvc_spring_postgres.entity.ticket.ticket_type;

import com.example.mvc_spring_postgres.entity.ticket.TicketEntiry;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@Data
@Entity
@Table(name="bus_ticket_001",
        indexes = {
                @Index(name = "idx_ticketId", columnList = "ticket_id"),
                @Index(name = "idx_chair", columnList = "chair_num"),
        })
@EnableJpaRepositories
@DynamicUpdate
@DynamicInsert
public class BusTicket {
    @Id
    @OneToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "id", columnDefinition = "UUID", nullable = false)
    private TicketEntiry ticketId;

    @Column(name = "chair_num", columnDefinition = "VARCHAR(255)", nullable = false)
    private String chairNum;

}
