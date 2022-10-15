package com.api.placesearch.api.repo;


import com.api.placesearch.api.entity.SearchLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SearchLogRepository extends JpaRepository<SearchLog, String> {     // <Entity, 기본키 타입>

    //like검색
    Page<SearchLog> findByKeywordLike(String keyword, Pageable pageable);


}
