package com.api.placesearch.repo;


import com.api.placesearch.api.entity.Place;
import com.api.placesearch.api.repo.SearchResultRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class SearchResultRepoTest {

    @Autowired
    private SearchResultRepository searchResultRepository;

    @Test
    @DisplayName("장소 검색 정보가 DB에 저장이 잘 되는지 확인")
    public void saveUser() {
        // given
        Place place = new Place();
        place.setKeyword("카카오프렌즈서울");
        place.setViews(50);

        // when
        Place savedLog = searchResultRepository.save(place);
        // then (SEQ가 잘저장되었는지)
        Assertions.assertThat(savedLog.getSeq()).isNotNull();
    }




}
