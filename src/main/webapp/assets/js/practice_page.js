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

var select_editor = function(k, language_id) {
    var problem;
    var skeleton_code;
    //selcet(k)로 접근: 문제1 문제2 버튼을 눌럿을 떄 language_id를 안주므로 첫번째 problem 값으로 셋팅
    if (language_id === undefined) {
        problem = $($("div[data-kth_problem_info=" + k + "]").get(0));
        language_id = problem.data("language_id").toString();
        skeleton_code = problem.data("skeleton_code");
    }
    //language option을 선택하여 problem을 바꿀 떄
    else{
        problem = $("div[data-kth_problem_info=" + k + "][data-language_id=" + language_id + "]");
        if(problem.size()==0){
            skeleton_code = "제공된 problemVo가 없다. 따라서 문법에 맞게 코딩해도 저장안되며 컴파일도 안됨";
        }
        else{
            skeleton_code = problem.data("skeleton_code");
        }
    }
    // 에디터 세팅
    var editor = ace.edit("editor-" + k);
    var mode;
    editor.setValue(skeleton_code);
    editor.setTheme("ace/theme/monokai");
    editor.$blockScrolling = Infinity;
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

    //전역변수 k값 갱신
    problem_id = problem.data("problem_id");
};

var save_code = function (k) {
    alert(k + '번 문제의 소스코드가 저장되었습니다 \n 는 훼이크고 사실 저장 안됨. practice 니까' )
};

var run_code = function(){
    alert(problem_id + '번 문제의 소스코드 컴파일후 실해인데 practice버전이라 구현안함');
};

//도움말 함수 호출. spotlight해준다
var help = function () {
    alert('이것은 튜토리얼 입니다');
    alert('run버튼을 누르면 컴파일 및 실행이됩니다');
    alert('시험보러가기를 누르면 시험을 볼 수 있다');
    alert('친절한 설명');
};

//제출 알림만 뜨고 페이지 이동은 없다
var final_submit = function () {
    alert('이 버튼을 누르면 최종 제출이 되며 테스트가 끝납니다');
};

//테스트 페이지로 이동
var goTest = function () {
    var a = confirm('시험보러간다?');
    if (a) {
        location.href = "/test";
    }
    else {
        alert('그래좀더 고민좀 해봐ㅣ라');
    }
};
