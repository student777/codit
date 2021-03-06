package repository;

import vo.SourceCodeVo;
import org.apache.ibatis.session.SqlSession;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SourceCodeRepository {
    @Autowired
    private SqlSession sqlSession;

    public int insert(SourceCodeVo sourceCodeVo) {
        // return sourceCodeVo pk
        sqlSession.insert("sourcecode.insert", sourceCodeVo);
        return sourceCodeVo.getId();
    }

    public SourceCodeVo getByApplicantAndProbleminfo(Map<String, Object> map) {
        return sqlSession.selectOne("sourcecode.selectByApplicantAndProblemInfo", map);
    }
}