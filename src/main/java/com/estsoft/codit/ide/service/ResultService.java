package com.estsoft.codit.ide.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ResultService {

  public Map getResult() {
    /*
    사용자의 uuid를 DB에 쿼리 날려서 결과 map 생성
    1) 사용자의 uuid 날림
    2) result 테이블에서 결과값 가져온다
    3) 텍스트로 쏴줄 거는 jsp에 넣어준다
    4-a) 그래프 사진 generate 해서 링크를 걸어준다
    4-b) 아니면 그래프에 들어갈 숫자만 jsp로 넘긴 다음 javascript로 그래프를 그려준다
         즉, front-end 단에서 시각화(렌더링)을 해줌
     */
    return null;
  }





}
