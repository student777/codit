package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.ProblemVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  public ProblemVo getByProblemInfoId(int problem_info_id, int language_id) {
    Map map = new HashMap<String, Integer>();
    map.put("problemInfoId", problem_info_id);
    map.put("languageId", language_id);
    return sqlSession.selectOne("problem.selectByProblemInfo", map);
  }
}
