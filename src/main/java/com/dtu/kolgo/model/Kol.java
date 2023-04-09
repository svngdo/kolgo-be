package com.dtu.kolgo.model;

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
public class Kol extends BaseInt {

    @OneToMany(mappedBy = "kol", cascade = CascadeType.ALL)
    private List<Image> images;
    @OneToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;
    @Column
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;
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
    @OneToMany(mappedBy = "kol", cascade = CascadeType.ALL)
    private List<Booking> bookings;
    @OneToMany(mappedBy = "kol", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;
    @OneToMany(mappedBy = "kol", cascade = CascadeType.ALL)
    private List<Payment> payments;
    @ManyToMany(mappedBy = "kols")
    private List<Campaign> campaigns;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
