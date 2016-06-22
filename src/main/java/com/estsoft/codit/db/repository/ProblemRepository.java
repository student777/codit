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

  public List<ProblemVo> getList(){
    return sqlSession.selectList("problem.selectAll");
  }

  public int insert(){
    return sqlSession.insert("problem.insert");
  }

  public ProblemVo get(int id){
    return sqlSession.selectOne("problem.selectById", id);
  }
}
