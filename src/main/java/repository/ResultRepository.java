package repository;

import vo.ResultVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultRepository {
    @Autowired
    private SqlSession sqlSession;

    public List<ResultVo> getList() {
        return sqlSession.selectList("result.selectAll");
    }

    public int insert(ResultVo resultVo) {
        return sqlSession.insert("result.insert", resultVo);
    }

    public ResultVo get(int id) {
        return sqlSession.selectOne("result.selectById", id);
    }

    public ResultVo getBySourceCodeTestCase(int sourceCodeId, int testCaseId) {
        ResultVo resultVo = new ResultVo();
        resultVo.setSourceCodeId(sourceCodeId);
        resultVo.setTestCaseId(testCaseId);
        return sqlSession.selectOne("result.selectBySourceCodeAndTestCase", resultVo);

    }

}
