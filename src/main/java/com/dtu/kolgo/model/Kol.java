package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "kols")
public class Kol extends BaseInt {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "phone")
    private String phone;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "field_id")
    private Field field;
    @Column(name = "post_price")
    private BigDecimal postPrice;
    @Column(name = "video_price")
    private BigDecimal videoPrice;
    @Column(name = "facebook_url")
    private String facebookUrl;
    @Column(name = "instagram_url")
    private String instagramUrl;
    @Column(name = "tiktok_url")
    private String tiktokUrl;
    @Column(name = "youtube_url")
    private String youtubeUrl;
    @OneToMany(mappedBy = "kol", cascade = CascadeType.ALL)
    private List<Image> images;
    @OneToMany(mappedBy = "kol", cascade = CascadeType.ALL)
    private List<Booking> bookings;
    //    @OneToMany(mappedBy = "kol", cascade = CascadeType.ALL)
//    private List<Payment> payments;
//    @OneToMany(mappedBy = "kol")
//    private List<Feedback> feedbacks;
    @ManyToMany(mappedBy = "kols")
    private List<Campaign> campaigns;

}
