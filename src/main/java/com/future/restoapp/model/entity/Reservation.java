package com.future.restoapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

//import com.future.restoapp.model.entity.*;

@Table(name = Reservation.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {
    public static final String TABLE_NAME = "reservations";

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_START_TIME = "start_time";

    @ManyToOne
    @JoinColumn(name = COLUMN_USER_ID, nullable = false, updatable = false)
    private User user;

    @Column(name = COLUMN_START_TIME)
    private Date startTime;

    @Column(name = COLUMN_DURATION)
    private Float duration;

    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<OrderItem> orders = new HashSet<>();

}
