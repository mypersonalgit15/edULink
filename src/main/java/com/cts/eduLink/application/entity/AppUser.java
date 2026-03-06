package com.cts.eduLink.application.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(unique = true, nullable = false)
    private Long phoneNumber;

    @OneToOne(mappedBy = "appUser")
    private AppUser role;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Notification> notificationList;
}
