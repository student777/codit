package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.ProblemVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProblemRepository {
  @Autowired
  private SqlSession sqlSession;

  public int insert(){
    return sqlSession.insert("problem.insert");
  }

  public ProblemVo get(int id){
    return sqlSession.selectOne("problem.selectById", id);
  }

  public List<ProblemVo> getByProblemInfoId(int problemInfoId) {
    return sqlSession.selectList("problem.selectByProblemInfo", problemInfoId);
  }
}
