package com.api.placesearch.api.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@DynamicUpdate // JPA 더티체킹간 변경한 필드만 대응
@Table(name = "T_PLACE_SEARCH_INFO")
public class Place {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "KEYWORD")
    private String keyword;

    @Column(name = "VIEWS")
    private Integer views;

    @Column(name = "UPDATED_AT")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime updatedAt;

    @Column(name = "CREATED_AT")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime createdAt;

    public Place(){

    }
    public Place(String keyword, Integer views, LocalDateTime createdAt) {
        this.keyword = keyword;
        this.views = views;
        this.createdAt = createdAt;
    }


    public void update(Long seq, String keyword, Integer views, LocalDateTime createdAt) {
        this.seq = seq;
        this.keyword = keyword;
        this.views = views;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = createdAt;
    }
}
