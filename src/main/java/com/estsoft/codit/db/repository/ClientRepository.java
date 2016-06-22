package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.ClientVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Repository
public class ClientRepository {
  @Autowired
  private SqlSession sqlSession;

  public List<ClientVo> getList(){
    return sqlSession.selectList("client.selectAll");
  }

  public int insert(){
    return sqlSession.insert("client.insert");
  }

  public ClientVo get(int id){
    return sqlSession.selectOne("client.selectById", id);
  }

}
