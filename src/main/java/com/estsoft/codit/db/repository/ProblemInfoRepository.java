package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.ProblemInfoVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

  public List<Integer> getByApplicantId(int id){
    List<Integer> list = sqlSession.selectList("probleminfo.selectByApplicantId", id);
    return list;
  }

  /*
  튜토리얼 할때 쓰는 더미 문제들
   */
  public List<Integer> getPracticeList() {
    List<Integer> list = new ArrayList<Integer>();
    list.add(1);
    list.add(2);
    list.add(3);
    return list;
  }
}
