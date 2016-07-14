package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.SourceCodeVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SourceCodeRepository {
  @Autowired
  private SqlSession sqlSession;

  public List<SourceCodeVo> getList(){
    return sqlSession.selectList("sourcecode.selectAll");
  }

  public int insert(SourceCodeVo sourceCodeVo){
    return sqlSession.insert("sourcecode.insert", sourceCodeVo);
  }

  public SourceCodeVo get(int id){
    return sqlSession.selectOne("sourcecode.selectById", id);
  }

  public SourceCodeVo getByApplicantAndProblem(SourceCodeVo sourceCodeVo) {
    return sqlSession.selectOne("sourcecode.selectByRun", sourceCodeVo) ;
  }

  public List<SourceCodeVo> getByApplicant(int id) {
    return sqlSession.selectList("sourcecode.selectByApplicant", id);
  }
}
