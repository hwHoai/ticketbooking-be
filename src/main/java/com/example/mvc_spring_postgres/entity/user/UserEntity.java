package com.example.mvc_spring_postgres.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@Data
@Entity
@Table(name="user_001")
@EnableJpaRepositories
@DynamicUpdate
@DynamicInsert
public class UserEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "user_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String userName;

    @Column(name = "user_email", columnDefinition = "VARCHAR(255)", nullable = false)
    private String userEmail;

    @Column(name = "user_password", columnDefinition = "VARCHAR(255)", nullable = false)
    private String userPassword;

    public UserEntity(String userName, String userEmail,  String password) {
        this.userName = userName;
        this.userPassword = password;
        this.userEmail = userEmail; // Default email, can be changed later
    }

    public UserEntity() {

    }
}
