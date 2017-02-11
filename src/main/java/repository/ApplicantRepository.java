package repository;

import vo.ApplicantVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicantRepository {
    @Autowired
    private SqlSession sqlSession;

    public ApplicantVo get(int id) {
        return sqlSession.selectOne("applicant.selectById", id);
    }

    public ApplicantVo getBySecretKey(String secretKey) {
        return sqlSession.selectOne("applicant.selectBySecretKey", secretKey);
    }
}
