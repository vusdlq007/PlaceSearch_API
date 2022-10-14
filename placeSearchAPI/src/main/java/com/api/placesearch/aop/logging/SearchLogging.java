package com.api.placesearch.aop.logging;


import com.api.placesearch.api.dto.SearchInfoResponseDTO;
import com.api.placesearch.api.dto.SearchLogDTO;
import com.api.placesearch.api.dto.SearchResponseDTO;
import com.api.placesearch.api.svc.SearchPlaceLogService;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Slf4j
@Aspect
public class SearchLogging {

    @Autowired
    SearchPlaceLogService logService;


    // 해당방식은 가로챈 메서드에 리턴타입이 있다면 Around에서 받아서 직접 리턴하기때문에 리턴값도 정해줘야한다.
    @Around("within(com.api.placesearch.api.ctr..*)")
    public Object around(ProceedingJoinPoint jpt) throws Throwable {
        Object responseDTO = null;
        try {
            log.debug("메서드 시작전 AOP 메소드 호출.");

            // 핵심기능 메소드 수행
            responseDTO = jpt.proceed();

            if(responseDTO instanceof SearchResponseDTO){

                try {
                    SearchResponseDTO response = (SearchResponseDTO) responseDTO;
                    // 임시 구현. 추후 정보 넘겨받으면 셋팅.
                    SearchLogDTO searchLog = new SearchLogDTO.Builder(response.getKeyword(),"window10")
                            .macAdd("ACB-DDS-123-vvfd")
                            .createdAd(LocalDateTime.now())
                            .build();



                    logService.putLog(searchLog);
                }catch (Exception e){
                    e.getMessage();
                    e.printStackTrace();
                    log.error("UserAction 로깅 중 에러발생. 로깅 스킵...");
                    throw e;
                }
            }


        } catch (Exception e) {
            log.error("AOP ERROR Message ["+e.getMessage()+"]");
            e.printStackTrace();
            log.error("AOP 실행중 에러발생.");
            throw e;
        }
        return responseDTO;
    }


}
