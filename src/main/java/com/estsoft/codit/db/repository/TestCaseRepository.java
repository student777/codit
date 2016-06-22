package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.TestCaseVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestCaseRepository {
  @Autowired
  private SqlSession sqlSession;

  public List<TestCaseVo> getList(){
    return sqlSession.selectList("testcase.selectAll");
  }

  public int insert(){
    return sqlSession.insert("testcase.insert");
  }

  public TestCaseVo get(int id){
    return sqlSession.selectOne("testcase.selectById", id);
  }
}
