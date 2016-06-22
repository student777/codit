package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.ResultVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultRepository {
  @Autowired
  private SqlSession sqlSession;

  public List<ResultVo> getList(){
    return sqlSession.selectList("result.selectAll");
  }

  public int insert(){
    return sqlSession.insert("result.insert");
  }

  public ResultVo get(int id){
    return sqlSession.selectOne("result.selectById", id);
  }
}
