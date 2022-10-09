package com.api.placesearch.api.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "T_SEARCH_LOG")
public class SearchLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "MAC_ADD")
    private String macAdd;

    @Column(name = "OS")
    private String os;

    @Column(name = "KEYWORD")
    private String keyword;

    @Column(name = "VIEWS")
    private Integer views;

    @Column(name = "CREATED_AT")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime createdAt;


}
