package com.future.restoapp.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.demo.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//import com.future.restoapp.company.entity.*;

@Table(name = Reservation.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {
    public static final String TABLE_NAME = "reservations";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_START_TIME = "start_time";

    @Column(name = Reservation.COLUMN_ID)
    private Integer id;

    @Column(name = Reservation.COLUMN_USER_ID)
    private Integer userID;

    @Column(name = Reservation.COLUMN_START_TIME)
    private Date startTime;

    @Column(name = Reservation.COLUMN_DURATION)
    private Float duration;

}
