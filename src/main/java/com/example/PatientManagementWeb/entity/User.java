package com.example.PatientManagementWeb.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;   //so all the subclasses can access to the variable
    protected String username;
    protected String password;

    protected String firstName;
    protected String lastName;
    protected String phone;


    @CreatedDate
    protected LocalDateTime createdAt;

    @LastModifiedDate
    protected LocalDateTime updatedAt;
}
