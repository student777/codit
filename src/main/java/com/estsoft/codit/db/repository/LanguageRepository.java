package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.LanguageVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageRepository {
  @Autowired
  private SqlSession sqlSession;

  public List<LanguageVo> getList(){
    return sqlSession.selectList("language.selectAll");
  }

  public int insert(){
    return sqlSession.insert("language.insert");
  }

  public LanguageVo get(int id){
    return sqlSession.selectOne("language.selectById", id);
  }

  public int getByProblemId(int id){
    sqlSession.selectOne("language.selectByProblemId", id);
    return sqlSession.selectOne("language.selectByProblemId", id);
  }

}
