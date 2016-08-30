package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.ApplicantVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ApplicantRepository {
  @Autowired
  private SqlSession sqlSession;

  public List<ApplicantVo> getList(){
    return sqlSession.selectList("applicant.selectAll");
  }

  public int insert(){
    return sqlSession.insert("applicant.insert");
  }

  public ApplicantVo get(int id){
    return sqlSession.selectOne("applicant.selectById", id);
  }

  public ApplicantVo getByTicket(String ticket) {
    return sqlSession.selectOne("applicant.selectByTicket", ticket);
  }

  public ApplicantVo getByRecruitIdEmail(Integer recruitId, String email) {
    HashMap<String , Object> map = new HashMap<String, Object>();
    map.put("recruitId", recruitId);
    map.put("email", email);
    return sqlSession.selectOne("applicant.selectByRecruitIdEmail", map);
  }

  public void setStartTime(ApplicantVo applicantVo){
    sqlSession.update("applicant.insertStartTime", applicantVo.getId());
  }

  public void setSubmitTime(ApplicantVo applicantVo) {
    sqlSession.update("applicant.insertSubmitTime", applicantVo.getId());
  }
}
