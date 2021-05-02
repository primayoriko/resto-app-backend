package com.future.restoapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//import com.future.restoapp.model.entity.*;

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