package com.future.restoapp.domain;

import com.future.restoapp.util.CopyUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

@Table(name = Reservation.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {

    public static final String TABLE_NAME = "reservations";

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_BOARD_ID = "board_id";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_END_TIME = "end_time";
    public static final String COLUMN_IS_ACCEPTED = "is_accepted";
    public static final String COLUMN_TOTAL_PRICE = "total_price";

    @ManyToOne
    @JoinColumn(name = COLUMN_USER_ID, nullable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = COLUMN_BOARD_ID, nullable = false, updatable = false)
    private Board board;

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

    @Transactional
    public void update(Reservation r){
        CopyUtil.copyNonNullProperties(r, this);
    }

}
