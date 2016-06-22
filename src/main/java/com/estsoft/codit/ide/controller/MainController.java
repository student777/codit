package com.estsoft.codit.ide.controller;

import com.estsoft.codit.db.repository.ApplicantRepository;
import com.estsoft.codit.db.repository.CartRepository;
import com.estsoft.codit.db.repository.ClientRepository;
import com.estsoft.codit.db.repository.LanguageRepository;
import com.estsoft.codit.db.repository.ProblemInfoRepository;
import com.estsoft.codit.db.repository.ProblemRepository;
import com.estsoft.codit.db.repository.RecruitRepository;
import com.estsoft.codit.db.repository.ResultRepository;
import com.estsoft.codit.db.repository.SourceCodeRepository;
import com.estsoft.codit.db.repository.TestCaseRepository;
import com.estsoft.codit.ide.service.MainService;
import com.estsoft.codit.ide.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 MainController- index page, instruction page, practice page 3가지의 URL을 처리한다
 IndexController, InstructionController 따로 안만들고 여기다 모아도 코드 별로 안길듯
 */
@Controller
public class MainController {

  @Autowired
  private MainService mainService;

  @Autowired
  private TestService testService;

  @Autowired
  private ApplicantRepository a;
  @Autowired
  private CartRepository b;
  @Autowired
  private ClientRepository c;
  @Autowired
  private LanguageRepository d;
  @Autowired
  private ProblemInfoRepository e;
  @Autowired
  private ProblemRepository f;
  @Autowired
  private RecruitRepository g;
  @Autowired
  private ResultRepository h;
  @Autowired
  private SourceCodeRepository i;
  @Autowired
  private TestCaseRepository j;


  /*
   루트 페이지로 index page를 보여준다
   parameter의 uuid와 수험생이 입력한 이름 이메일을 받아 DB 확인 후 세션을 부여한다
   */
  @RequestMapping("/")
  public String index() {
    //세션 부여
    mainService.idenfityApplicant();

    System.out.println("index page");

    return "index";
  }


  /*
   시험 중 유의사항에 대해 안내하는 페이지
   */
  @RequestMapping("/instruction")
  public String instruction() {


    /*
    * instruction page로 오면  CRUD  test 를 진행한다.
    System.out.println("=======================SELECT INSERT TEST==============================");
    System.out.println("client inserted: " + c.insert() );
    System.out.println("client selected id=1: " + c.get(1) );
    System.out.println("client list: "  + c.getList());

    System.out.println("recruit inserted: " + g.insert() );
    System.out.println("recruit selected id=1: " + g.get(1) );
    System.out.println("recruit list: "  + g.getList());

    System.out.println("applicant inserted: " + a.insert() );
    System.out.println("applicant selected id=1: " + a.get(1) );
    System.out.println("applicant list: "  + a.getList());

    System.out.println("language inserted: " + d.insert() );
    System.out.println("language selected id=1: " + d.get(1) );
    System.out.println("language list: "  + d.getList());

    System.out.println("probleminfo inserted: " + e.insert() );
    System.out.println("probleminfo selected id=1: " + e.get(1) );
    System.out.println("probleminfo list: "  + e.getList());

    System.out.println("problem inserted: " + f.insert() );
    System.out.println("problem selected id=1: " + f.get(1) );
    System.out.println("problem list: "  + f.getList());

    System.out.println("sourcecode inserted: " + i.insert() );
    System.out.println("sourcecode selected id=1: " + i.get(1) );
    System.out.println("sourcecode list: "  + i.getList());

    System.out.println("cart inserted: " + b.insert() );
    System.out.println("cart selected id=1: " + b.get(1) );
    System.out.println("cart list: "  + b.getList());

    System.out.println("testcase selected id=1: " + j.get(1) );
    System.out.println("testcase inserted: " + j.insert() );
    System.out.println("testcase list: "  + j.getList());

    System.out.println("result inserted: " + h.insert() );
    System.out.println("result selected id=1: " + h.get(1) );
    System.out.println("result list: "  + h.getList());
    System.out.println("=====================================TEST END=========================================");
    */

    return "instruction";
  }


  /*
 sandbox(?)
 IDE 콘솔을 그려주는데 cart
 여기서는 저장버튼을 눌러도 저장안된다 물론 유저입장에서는 저장된것 처럼 느끼게 한다
 complie and run, output 콘솔에 append 기능만 지원
 testService를 갖다 쓴다
 */
  @RequestMapping("/practice")
  public String practice() {
    return "practice";
  }


}
