package com.api.placesearch.api.repo;


import com.api.placesearch.api.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SearchResultRepository extends JpaRepository<Place, String> {     // <Entity, 기본키 타입>

//    Optional<Place> findById(String id);

//    List<Place> findByName(String name);

    //like검색
//    List<Place> findByNameLike(String name);


}
