package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
    @Column(name = "introduction")
    private String introduction;
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
    @Column(name = "facebook_followers_count")
    private String facebookFollowersCount;
    @Column(name = "instagram_followers_count")
    private String instagramFollowersCount;
    @Column(name = "tiktok_followers_count")
    private String tiktokFollowersCount;
    @Column(name = "youtube_subscribers_count")
    private String youtubeSubscribersCount;
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

    public String getTikTokFollowersCount() {
        return getFollowersCount(tiktokUrl, "strong[data-e2e='followers-count']");
    }

    public String getFacebookFollowersCount() {
        return getFollowersCount(facebookUrl, "a.x1i10hfl.xjbqb8w.x6umtig.x1b1mbwd.xaqea5y.xav7gou.x9f619.x1ypdohk.xt0psk2.xe8uvvx.xdj266r.x11i5rnm.xat24cr.x1mh8g0r.xexx8yu.x4uap5.x18d9i69.xkhd6sd.x16tdsg8.x1hl2dhg.xggy1nq.x1a2a7pz.xt0b8zv.xi81zsa.x1s688f");
    }

    private String getFollowersCount(String url, String cssQuery) {
        if (url == null || url.isEmpty()) {
            return null;
        }
        try {
            // Fetch the TikTok profile page
            Document document = Jsoup.connect(url).get();

            // Extract the follower count element
            Element followerCountElement = document.selectFirst(cssQuery);

            if (followerCountElement != null) {
                // Get the follower count value
                return followerCountElement.text();
            } else {
                return null;
            }
        } catch (Exception e) {
            return "Error fetching follower count: " + e.getMessage();
        }
    }

}
