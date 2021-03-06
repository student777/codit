package repository;

import vo.ResultVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vo.SourceCodeVo;
import vo.TestCaseVo;

import java.util.List;
import java.util.Map;

@Repository
public class ResultRepository {
    @Autowired
    private SqlSession sqlSession;

    public int insert(ResultVo resultVo) {
        return sqlSession.insert("result.insert", resultVo);
    }

    public List<ResultVo> getBySourceCode(SourceCodeVo sourceCodeVo) {
        return sqlSession.selectList("result.selectBySourceCode", sourceCodeVo);
    }

    public Map<String, Object> getRecordsByTestCaseAndMemory(TestCaseVo testCaseVo) {
        return sqlSession.selectOne("result.selectRecordByTestCaseAndMemory", testCaseVo);
    }
    public Map<String, Object> getRecordsByTestCaseAndRunningTime(TestCaseVo testCaseVo) {
        return sqlSession.selectOne("result.selectRecordByTestCaseAndRunningTime", testCaseVo);
    }
}