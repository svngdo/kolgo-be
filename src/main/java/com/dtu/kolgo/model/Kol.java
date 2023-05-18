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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;
    @Column(name = "address_details")
    private String addressDetails;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "kol_fields",
            joinColumns = @JoinColumn(name = "kol_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "field_id", referencedColumnName = "id"))
    private List<Field> fields;
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "kol_images",
            joinColumns = @JoinColumn(name = "kol_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> images;
    @OneToMany(mappedBy = "kol", cascade = CascadeType.ALL)
    private List<Booking> bookings;
    @ManyToMany(mappedBy = "kols")
    private List<Campaign> campaigns;

    public List<Short> getFieldIds() {
        return fields.stream().map(BaseShort::getId).toList();
    }

    public List<String> getFieldNames() {
        return fields.stream().map(Field::getName).toList();
    }

    public List<String> getImageNames() {
        return images.stream().map(Image::getName).toList();
    }

}
