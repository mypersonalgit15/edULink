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

    private String userName;
    private String userEmail;
    private Long phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "appUser")
    private Student student;

    @OneToOne(mappedBy = "appUser")
    private Faculty faculty;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Notification> notificationList;
}
