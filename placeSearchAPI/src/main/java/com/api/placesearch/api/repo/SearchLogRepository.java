package com.api.placesearch.api.repo;


import com.api.placesearch.api.entity.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SearchLogRepository extends JpaRepository<SearchLog, String> {     // <Entity, 기본키 타입>

//    Optional<SearchLog> findById(Integer id);

//    List<SearchLog> findByName(String name);

    //like검색
//    List<SearchLog> findByNameLike(String name);


}
