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