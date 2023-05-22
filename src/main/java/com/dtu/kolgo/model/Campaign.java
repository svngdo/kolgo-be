package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.CampaignStatus;
import com.dtu.kolgo.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "campaigns")
public class Campaign extends BaseInt {

    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "campaign_fields",
            joinColumns = @JoinColumn(name = "campaign_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "field_id", referencedColumnName = "id"))
    private List<Field> fields;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "finish_time")
    private LocalDateTime finishTime;
    @Column(name = "location")
    private String location;
    @Column(name = "description")
    private String description;
    @Column(name = "details")
    private String details;
    @Enumerated(EnumType.STRING)
    private CampaignStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "campaign_images",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> images;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "campaign_kols",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "kol_id"))
    private List<Kol> kols;

    public String getTimestamp() {
        return DateTimeUtils.convertToString(timestamp);
    }

    public String getStartTime() {
        return DateTimeUtils.convertToString(startTime);
    }

    public String getFinishTime() {
        return DateTimeUtils.convertToString(finishTime);
    }

    public List<String> getImageNames() {
        return images.stream().map(Image::getName).toList();
    }

    public List<Short> getFieldIds() {
        return fields.stream().map(BaseShort::getId).toList();
    }

}
