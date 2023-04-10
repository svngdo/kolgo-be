package com.dtu.kolgo.model;

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
    private LocalDateTime time;
    @Column
    private String location;
    @Column
    private int cost;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "campaigns_kols",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "kol_id"))
    private List<Kol> kols;

}
