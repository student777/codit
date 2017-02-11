package repository;

import vo.LanguageVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LanguageRepository {
    @Autowired
    private SqlSession sqlSession;

    public LanguageVo get(int id) {
        return sqlSession.selectOne("language.selectById", id);
    }

    public int getByProblemId(int id) {
        sqlSession.selectOne("language.selectByProblemId", id);
        return sqlSession.selectOne("language.selectByProblemId", id);
    }

}
