package com.future.restoapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
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
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_END_TIME = "end_time";
    public static final String COLUMN_IS_ACCEPTED = "is_accepted";
    public static final String COLUMN_TOTAL_PRICE = "total_price";

    @ManyToOne
    @JoinColumn(name = COLUMN_USER_ID, nullable = false, updatable = false)
    private User user;

    @Column(name = COLUMN_START_TIME, columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime startTime;

    @Column(name = COLUMN_END_TIME, columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime endTime;

    @Column(name = COLUMN_IS_ACCEPTED, nullable = false)
    private Boolean isAccepted = false;

    @Column(name = COLUMN_TOTAL_PRICE, nullable = false)
    private Float totalPrice = 0F;

    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<OrderItem> orders = new HashSet<>();

}
