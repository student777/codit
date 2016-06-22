package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.CartVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepository {
  @Autowired
  private SqlSession sqlSession;

  public List<CartVo> getList(){
    return sqlSession.selectList("cart.selectAll");
  }

  public int insert(){
    return sqlSession.insert("cart.insert");
  }

  public CartVo get(int id){
    return sqlSession.selectOne("cart.selectById", id);
  }
}
