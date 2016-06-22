package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.RecruitVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecruitRepository {
  @Autowired
  private SqlSession sqlSession;

  public List<RecruitVo> getList(){
    return sqlSession.selectList("recruit.selectAll");
  }

  public int insert(){
    return sqlSession.insert("recruit.insert");
  }

  public RecruitVo get(int id){
    return sqlSession.selectOne("recruit.selectById", id);
  }

}
