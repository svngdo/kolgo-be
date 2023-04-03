package com.dtu.kolgo.model;

import com.dtu.kolgo.util.constant.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "kols")
public class Kol extends Base {

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String speciality;
    @Column
    private String facebookUrl;
    @Column
    private String instagramUrl;
    @Column
    private String tiktokUrl;
    @Column
    private String youtubeUrl;
    @Column
    private String previousCollaboration;
    @OneToMany(mappedBy = "kol", cascade = CascadeType.ALL)
    private List<Booking> bookings;
    @OneToMany(mappedBy = "kol", cascade = CascadeType.ALL)
    private List<Payment> payments;
    @ManyToMany(mappedBy = "kols")
    private List<Campaign> campaigns;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
