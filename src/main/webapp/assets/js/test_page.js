var load_code = function () {
    var editor = ace.edit("editor");
    $.ajax({
        url: '/test/load',
        type: "post",
        data: {"problem_id": problem_id, "applicant_id": applicant_id},
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
    {target: "#select-problem", msg: 'Click this button to switch task'},
    {target: "#top-bar > .input-field.col.s2.no-padding", msg: 'You can choose one of three languages'},
    {target: "#save-code", msg: 'Save current source code. shortcut: ctrl+S'},
    {target: "#select-testcase", msg: 'You can test source code with input'},
    {target: "#run-code", msg: 'Execute code(auto saved) shortcut: ctrl+R'},
    {target: "#load-code", msg: 'Load last saved code. If you close browser by mistake, this button will salvage you'},
    {target: "#final-submit", msg: 'Submit your solution and finish test'},
    {target: ".timer", msg: 'After given time, last saved source codes are automatically submitted'},
    {target: "#btn-help", msg: 'To click this button, you can review tutorial'},
];

