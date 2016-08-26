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

    //set language_id, problem, problem_id, skeleton_code
    //select(k)로 접근: 문제1 제문2 버튼을 눌럿을 떄 language_id를 안주므로 첫번째 problem 값으로 셋팅
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
            skeleton_code = "There is no Problem";
            //전역변수 k값 갱신
            problem_id = undefined;
        }
        else {
            skeleton_code = problem['skeleton_code'];
            //전역변수 k값 갱신
            problem_id = problem["problem_id"];
        }
    }

    // ace editor에 skeleton_code 던져줌
    var editor = ace.edit("editor");
    editor.$blockScrolling = Infinity;
    var mode;
    editor.setValue(skeleton_code);
    editor.setTheme("ace/theme/monokai");
    // editor.setOptions({
    //     fontFamily: "'Monaco', 'Menlo', 'Ubuntu Mono', 'Consolas'",
    //     fontSize: "12pt"
    // });
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
var save_code = function () {
    var editor = ace.edit("editor");
    var code = editor.getValue();
    $.ajax({
       url: '/test/save',
       type: "post",
       //리턴값은 'success' or 'fail'
       data: {"code": code, "problem_id": problem_id },
       success: function (response) {
           if (response == 'success') {
               alert('saved');
           }
           else if (response == 'fail') {
               alert('save fail');
           }
       },
       error: function (xhr, status, error) {
           console.error(status + ":" + error);
       }
   });
};

var run_code = function () {
    var editor = ace.edit("editor");
    var code = editor.getValue();
    var test_case_id = $('select[name=test_cases]').get(current_k - 1).value;

    $.ajax({
        url: '/test/save',
        type: "post",
        data: {"code": code, "problem_id": problem_id },
        success: function (response) {
            if (response == 'success') {
                $.ajax({
                    url: '/test/run',
                    type: "post",
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    //dataType: "json",
                    data: {
                        "problem_id": problem_id,
                        "applicant_id": applicant_id,
                        "test_case_id": test_case_id
                    },
                    success: function (response) {
                        $("#terminal-" + current_k).html(response.replace("\n", '<br>'));
                    },
                    error: function (xhr, status, error) {
                        console.error(status + ":" + error);
                    }
                });
            }
            else if (response == 'fail') {
                alert('save fail');
            }
        },
        error: function (xhr, status, error) {
            console.error(status + ":" + error);
        }
    });
};


var load_code = function () {
    var editor = ace.edit("editor");
    $.ajax({
        url: '/test/load',
        type: "post",
        data: { "problem_id": problem_id, "applicant_id": applicant_id},
        success: function (response) {
            if (response == 'fail') {
                alert('saved code does not exist');
            }
            else {
                editor.setValue(response);
            }
        },
        error: function (xhr, status, error) {
            console.error(status + ":" + error);
        }
    });
}

//최종 제출
var final_submit = function () {
    var a = confirm('really?');
    if (a) {
        $.ajax({
                   url: '/test/submit',
                   type: "post",
                   contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                   //dataType: "json",
                   success: function () {
                       alert("good job");
                       location.href = "/result";
                   },
                   error: function (xhr, status, error) {
                       console.error(status + ":" + error);
                   }
               });
    }
    else {
    }
};

//spotlight 관련 변수, 함수
var spotLightData = [
    { target:"#select-problem", msg:'Click this button to switch task' },
    { target:"#top-bar > .input-field.col.s2.no-padding", msg:'You can choose one of three languages' },
    { target:"#save-code", msg:'Save current source code. shortcut: ctrl+S' },
    { target:"#select-testcase", msg:'You can test source code with input' },
    { target:"#run-code", msg:'Execute code(auto saved) shortcut: ctrl+R' },
    { target:"#load-code", msg:'Load last saved code. If you close browser by mistake, this button will salvage you' },
    { target:"#final-submit", msg:'Submit your solution and finish test' },
    { target:".timer", msg:'After given time, last saved source codes are automatically submitted' },
    { target:"#btn-help", msg:'To click this button, you can review tutorial' },
];
var index = 0;
//도움말 함수 호출. spotlight해준다
var help = function(){
    var dataLength = spotLightData.length;
    if( index == dataLength ){
        index=0;
        $("#dialog-confirm").dialog( "close" );
        $(".spotlight-background").remove();
        return;
    }
    if( index < dataLength ) {
        var options = {msg: spotLightData[index].msg, index:index };
        $(spotLightData[index].target).spotlight(options);
        index++;
    }
};
