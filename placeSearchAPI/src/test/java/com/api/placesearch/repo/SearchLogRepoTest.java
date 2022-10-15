package com.api.placesearch.repo;


import com.api.placesearch.api.entity.SearchLog;
import com.api.placesearch.api.repo.SearchLogRepository;

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
public class SearchLogRepoTest {

    @Autowired
    private SearchLogRepository searchLogRepository;

    @Test
    @DisplayName("로그 정보가 DB에 저장이 잘 되는지 확인")
    public void saveUser() {
        // given
        SearchLog searchLog = new SearchLog();
        searchLog.setKeyword("카카오프렌즈서울");
        searchLog.setMacAdd("PPLD-as53-AcvFG-AAGHP");
        searchLog.setOs("ios 14.2");

        // when
        SearchLog savedLog = searchLogRepository.save(searchLog);
        // then (SEQ가 잘저장되었는지)
        Assertions.assertThat(savedLog.getSeq()).isNotNull();
    }




}
