package com.future.restoapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;


@Table(name = Board.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {

    public static final String TABLE_NAME = "boards";

    public static final String COLUMN_X = "x";
    public static final String COLUMN_Y = "y";
    public static final String COLUMN_LENGTH = "length";
    public static final String COLUMN_WIDTH = "width";
    public static final String COLUMN_CAPACITY = "capacity";

    @Column(name = Board.COLUMN_X)
    private Integer x;

    @Column(name = Board.COLUMN_Y)
    private Integer y;

    @Column(name = Board.COLUMN_LENGTH)
    private Integer length;

    @Column(name = Board.COLUMN_WIDTH)
    private Integer width;

    @Column(name = Board.COLUMN_CAPACITY)
    private Integer capacity;

    @OneToMany(
            targetEntity = Reservation.class,
            mappedBy = "board",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = false
    )
    private Collection<Reservation> reservations = new HashSet<>();

}
