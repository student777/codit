package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import repository.ProblemInfoRepository;
import vo.ProblemInfoVo;
import java.util.List;


@Service
public class MainService {
    @Autowired
    private ProblemInfoRepository problemInfoRepository;

    public void getProblemInfoList(Model model){
        List<ProblemInfoVo> problemInfoVoList = problemInfoRepository.getList();
        model.addAttribute("problemInfoList", problemInfoVoList);
    }
}