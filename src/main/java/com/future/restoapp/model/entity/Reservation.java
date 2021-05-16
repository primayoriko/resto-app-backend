package com.future.restoapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = Reservation.COLUMN_USER_ID)
    private Integer userID;

    @Column(name = Reservation.COLUMN_START_TIME)
    private Date startTime;

    @Column(name = Reservation.COLUMN_DURATION)
    private Float duration;

    @OneToMany(mappedBy = TABLE_NAME)
    private Set<Menu> orders = new HashSet<>();

}
