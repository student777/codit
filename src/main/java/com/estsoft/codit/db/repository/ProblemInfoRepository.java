package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.ProblemInfoVo;
import com.estsoft.codit.db.vo.ProblemVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProblemInfoRepository {
  @Autowired
  private SqlSession sqlSession;

  public List<ProblemInfoVo> getList(){
    return sqlSession.selectList("probleminfo.selectAll");
  }

  public int insert(){
    return sqlSession.insert("probleminfo.insert");
  }

  public ProblemInfoVo get(int id){
    return sqlSession.selectOne("probleminfo.selectById", id);
  }
}
