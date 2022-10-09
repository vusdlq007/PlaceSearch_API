package com.api.placesearch.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
//@Entity
//@Table(name = "T_PLACE_DETAIL")
public class PlaceDetail implements Serializable {


    /**
     * 해당 테이블의 데이터들의 고유식별을 위해 복합키 구현.
     */

    @Id
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Id
    @Column(name = "IS_DEPOSIT")        // 입출금 여부 Y: 입금, N: 출금
    private String isDeposit;

    @Id
    @Column(name = "DEPOSIT_AMOUNT")    // 입금액
    private Long depositAmount;

    @Id
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;


}
