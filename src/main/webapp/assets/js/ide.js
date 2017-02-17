var select_editor = function (language_id) {
    //save current working code at problem_json_list
    if (problem_id != undefined) {
        var editor = ace.edit("editor");
        var code = editor.getValue();
        problem_json_list.filter(function (item) {
            return item.problem_id == problem_id;
        })[0]['skeleton_code'] = code;
    }
    var problem;
    var skeleton_code;

    //set language_id, problem, problem_id, skeleton_code
    problem = problem_json_list.filter(function (item) {
        return item.language_id == language_id;
    })[0];
    if (problem == undefined) {
        skeleton_code = "There is no Problem";
        //update global variable
        problem_id = undefined;
    }
    else {
        skeleton_code = problem['skeleton_code'];
        //update global variable
        problem_id = problem["problem_id"];
    }

    // set skeleton_code on ace editor
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

    //update option select value
    $('select[name=language]').get(0).value = language_id;
};

var run_code = function () {
    var editor = ace.edit("editor");
    var code = editor.getValue();
    var test_case_id = $('select[name=test_cases]').get(0).value;
    $.ajax({
        url: '/test/run',
        type: "post",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        //dataType: "json",
        data: {
            "code": code,
            "problem_id": problem_id,
            "test_case_id": test_case_id
        },
        success: function (response) {
            $("#terminal div div").html(response.replace("\n", '<br>'));
        },
        error: function (xhr, status, error) {
            console.error(status + ":" + error);
        }
    });
};


var index = 0;
//도움말 함수 호출. spotlight해준다
var help = function () {
    var dataLength = spotLightData.length;
    if (index == dataLength) {
        index = 0;
        $("#dialog-confirm").dialog("close");
        $(".spotlight-background").remove();
        return;
    }
    if (index < dataLength) {
        var options = {msg: spotLightData[index].msg, index: index};
        $(spotLightData[index].target).spotlight(options);
        index++;
    }
};


//replace new_alert to dialog
var new_alert = function(msg){
    $("#dialog-alert").text(msg);
    $("#dialog-alert").dialog({
        modal: true,
        buttons: {
            Ok: function () {
                $(this).dialog('close');
            }
        }
    });
};

//최종 제출
var final_submit = function(problem_info_id) {
    var editor = ace.edit("editor");
    var code = editor.getValue();
    var a = confirm('really?');
    if (a) {
        $.ajax({
            url: '/test/submit',
            type: "post",
            data: {code: code, problem_id: problem_id},
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (response) {
                alert(response);
                location.href = "/result/" + problem_info_id;
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
    {target: "#select-problem", msg: 'Click this button to switch task'},
    {target: "#top-bar > .input-field.col.s2.no-padding", msg: 'You can choose one of three languages'},
    {target: "#save-code", msg: 'Save current source code. shortcut: ctrl+S'},
    {target: "#select-testcase", msg: 'You can test source code with input'},
    {target: "#run-code", msg: 'Execute code(auto saved) shortcut: ctrl+R'},
    {target: "#final-submit", msg: 'Submit your solution and finish test'},
    {target: ".timer", msg: 'After given time, last saved source codes are automatically submitted'},
    {target: "#btn-help", msg: 'To click this button, you can review tutorial'},
];


//Initialize test: Set timer, keybind, first problem
$(function () {
    new_alert('Test starts');
    $('select').material_select(); //initialize css
    $('.timer').startTimer({
        onComplete: function () {
            new_alert('Time over, source code you finally run will be automatically submitted');
            final_submit(problem_id);
        }
    });
    //shortcut: ctrl + R
    $(document).bind('keydown', function (e) {
        if (e.ctrlKey && (e.which == 82)) {
            e.preventDefault();
            run_code();
            return false;
        }
    });
    select_editor('1');
});