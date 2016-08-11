//k번째 문제로 셋팅. 1부터 시작한다
var select = function (k) {
    //모든 selectable의 자식들을 hide하고 k번째 문제에 해당되는 것만 show
    for (var i = 1; i <= number_of_problems; i++) {
        $('.selectable > :nth-child(' + i + ')').hide();
    }
    $('.selectable > :nth-child(' + k + ')').show();
    //에디터는 따로 함수를 실행해줘야 렌더링된다
    select_editor(k);
    //전역변수 k값 갱신
    current_k = k;
};

var select_editor = function (k, language_id) {
    //현재 작업 내역 JSON 객체에 저장

    if(problem_id!=undefined){
        var editor = ace.edit("editor");
        var code = editor.getValue();
        problem_json_list.filter(function(item){
                                    return item.problem_id==problem_id;
                                })[0]['skeleton_code']=code;
    }
    var problem;
    var skeleton_code;
    //selcet(k)로 접근: 문제1 제문2 버튼을 눌럿을 떄 language_id를 안주므로 첫번째 problem 값으로 셋팅
    if (language_id === undefined) {
        problem = problem_json_list.filter(function(item){
            return item.kth_problem_info==k;
        })[0];
        language_id = problem['language_id'].toString();
        skeleton_code = problem['skeleton_code'];
        //전역변수 k값 갱신
        problem_id = problem["problem_id"];
    }
    //language option을 선택하여 problem을 바꿀 떄
    else {
        problem = problem_json_list.filter(function(item){
            return item.kth_problem_info==k;
        }).filter(function(item){
            return item.language_id==language_id;
        })[0];
        if (problem == undefined){
            skeleton_code = "제공된 problemVo가 없다. 따라서 문법에 맞게 코딩해도 저장안되며 컴파일도 안됨";
            //전역변수 k값 갱신
            problem_id = undefined;
        }
        else {
            skeleton_code = problem['skeleton_code'];
            //전역변수 k값 갱신
            problem_id = problem["problem_id"];
        }
    }
    // 에디터 세팅
    var editor = ace.edit("editor");
    editor.$blockScrolling = Infinity;
    var mode;
    editor.setValue(skeleton_code);
    editor.setTheme("ace/theme/monokai");
    switch (language_id) {
        case '1':
            mode = "ace/mode/c_cpp";
            break;
        case '2':
            mode = "ace/mode/java";
            editor.getSession().setMode("ace/mode/java");
            break;
        case '3':
            mode = "ace/mode/python";
            editor.getSession().setMode("ace/mode/python");
            break;
        default:
            mode = "ace/mode/text";
            break;
    }
    editor.getSession().setMode(mode);

    //option select값 변화
    $('select[name=language]').get(0).value = language_id;


};

//k번째 에디터 상의 소스코드 저장
var save_code = function (k) {
    var editor = ace.edit("editor");
    var code = editor.getValue();
    //ajax는 비동기식이라 success시 source_code ID를 받아서 save_code()로 리턴하는거 안됨
    //ajax POST
    $.ajax({
               url: '${pageContext.request.contextPath }/test/save',
               type: "post",
               //리턴값은 'success' or 'fail'
               //dataType: "json",
               data: {"code": code, "problem_id": problem_id, "applicant_id": applicant_id},
               success: function (response) {
                   if (response == 'fail') {
                       alert('저장 실패');
                   }
                   else if (response == 'success') {
                       alert('저장되었습니다');
                   }
               },
               error: function (xhr, status, error) {
                   console.error(status + ":" + error);
               }
           });
};

var run_code = function (k) {
    //일단 저장 후 돌림
    //TODO: 저장 후 돌려야 하는데 꼬이는거같다
    //save_code(k);
    test_case_id = $('select[name=test_cases]').get(current_k - 1).value;

    //ajax POST
    $.ajax({
               url: '${pageContext.request.contextPath }/test/run',
               type: "post",
               contentType: "application/x-www-form-urlencoded; charset=UTF-8",
               //dataType: "json",
               data: {
                   "problem_id": problem_id,
                   "applicant_id": applicant_id,
                   "test_case_id": test_case_id
               },
               success: function (response) {
                   $("#terminal-" + k).text(response);
               },
               error: function (xhr, status, error) {
                   console.error(status + ":" + error);
               }
           });
};

//최종 제출
var final_submit = function () {
    var a = confirm('최종 제출하시겠습니까?');
    if (a) {
        $.ajax({
                   url: '${pageContext.request.contextPath }/test/submit',
                   type: "post",
                   contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                   //dataType: "json",
                   success: function () {
                       alert("수고하셨습니다 submit_time 등록됨");
                       alert("채점이 다 끝나버렸네?");
                       location.href = "/result";
                   },
                   error: function (xhr, status, error) {
                       console.error(status + ":" + error);
                   }
               });
    }
    else {
        alert('그래좀더 고민좀 해봐ㅣ라');
    }
};

//도움말 함수 호출. spotlight해준다
var help = function () {
    alert('이것은 test의 도움말 입니다');
    alert('run버튼을 누르면 컴파일 및 실행이됩니다');
    alert('주어진 시간은 ${totalTime}분이며 시간이 지나면 마지막 저장본으로 자동 제출됩니다');
    alert('요이땅');
};