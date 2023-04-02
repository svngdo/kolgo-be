package com.dtu.kolgo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tokens")
public class Token extends Base {

    @Column
    private String value;
    @Column
    private LocalDateTime expiresIn;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
