package com.api.placesearch.api.repo;


import com.api.placesearch.api.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface SearchResultRepository extends JpaRepository<Place, String> {     // <Entity, 기본키 타입>

    Optional<Place> findBySeq(String seq);

    Optional<Place> findByKeyword(String keyword);

    Page<Place> findByOrderByViewsDesc(Pageable pageable);

    //like검색
//    List<Place> findByNameLike(String name);


}
