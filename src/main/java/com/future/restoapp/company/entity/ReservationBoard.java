package com.future.restoapp.company.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import com.future.restoapp.company.entity.*;

@Table(name = ReservationBoard.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationBoard implements Serializable {
    public static final String TABLE_NAME = "reservation_boards";
    public static final String COLUMN_BOARD_ID = "board_id";
    public static final String COLUMN_RESERVATION_ID = "reservation_id";

    @Id
    @Column(name = ReservationBoard.COLUMN_BOARD_ID)
    private Integer boardID;

    @Id
    @Column(name = ReservationBoard.COLUMN_RESERVATION_ID)
    private Integer reservationID;
}