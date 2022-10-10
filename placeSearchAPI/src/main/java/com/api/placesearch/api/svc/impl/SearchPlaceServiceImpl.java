package com.api.placesearch.api.svc.impl;

import com.api.placesearch.api.dto.SearchResponseDTO;
import com.api.placesearch.api.dto.SearchRequestDTO;
import com.api.placesearch.api.repo.SearchResultRepository;
import com.api.placesearch.api.svc.SearchPlaceService;
import com.api.placesearch.api.entity.Place;
import com.api.placesearch.cmm.constant.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@Transactional
public class SearchPlaceServiceImpl implements SearchPlaceService {

    @Value("${custom.service.timezone}")
    String timeZone;

    @Autowired
    SearchResultRepository searchResultRepository;


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");


    public SearchPlaceServiceImpl(SearchResultRepository searchResultRepository){
        this.searchResultRepository = searchResultRepository;
    }

//    /**
//     * 사용자 정보 추가.
//     *
//     * @param requestDTO
//     * @return
//     */
//    @Override
//    public UserResponseDTO userRegist(UserRequestDTO requestDTO) {
//
//        LocalDateTime curTime = LocalDateTime.parse(LocalDateTime.now(ZoneId.of(timeZone)).format(formatter));
//
//
//        UserVo userVo = new UserVo();
//
//        userVo.setName(requestDTO.getUserName());
//        userVo.setAge(requestDTO.getAge());
//        userVo.setCreatedAt(curTime);
//
//
//        // 기본적으로 Transactional이 동작함.
//        userRepository.save(userVo);
//
//
//        return new UserResponseDTO(ResponseCode.USER_REGIST_SUCCESS.getErrorCode(), ResponseCode.USER_REGIST_SUCCESS.getMessage());
//    }
//
//    /**
//     * 사용자 정보를 모두 조회한다.
//     *
//     * @param pageable
//     * @return
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public UserResponseDTO userSearch(Pageable pageable) {
//
//        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
//
//        Page<UserVo> userVoPage = null;
//
//        userVoPage = userRepository.findAll(pageRequest);
//
//
//        return new UserResponseDTO(ResponseCode.USER_SEARCH_SUCCESS.getErrorCode(), ResponseCode.USER_SEARCH_SUCCESS.getMessage(), userVoPage.getContent());
//    }


    @Override
    public SearchResponseDTO searchPlace(String keyword, Integer disCnt, String sort) {
        WebClient client = WebClient.builder()
                .baseUrl("http://")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return null;
    }

    @Override
    public SearchResponseDTO searchPlaceDetail(SearchRequestDTO requestDTO) {
        return null;
    }
}
