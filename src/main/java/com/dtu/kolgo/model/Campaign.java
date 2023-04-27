package com.dtu.kolgo.model;

import com.dtu.kolgo.enums.CampaignStatus;
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

    @Column
    private int cost;
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    private CampaignStatus status;
    @Column
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "campaign_kols",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "kol_id"))
    private List<Kol> kols;

}
