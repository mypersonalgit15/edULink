package com.cts.eduLink.application.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String statusType; // e.g., "Active", "Inactive", "Pending"
    private String description;

    // Perhaps generic, or link to specific entities
}