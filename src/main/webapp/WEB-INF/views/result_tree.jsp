<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/0630_favicon_beige.ico">
    <link rel="stylesheet" href="/assets/tree/vtree.css" type="text/css"/>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialize-custom.css"
          media="screen,projection"/>
    <script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath }/assets/ace/ace.js" type="text/javascript"  charset="utf-8"></script>
    <script src="/assets/tree/layout.vtree.js"></script>
    <script src="/assets/tree/vtree.js"></script>
    <style>
        fieldset {
            margin: 8px;
            padding: 4px;
            height: 68px;
            float: left;
            display: inline;
            white-space: nowrap;
        }
        fieldset input {
            margin: 4px;
            padding: 4px;
        }
        .hidden{
            display: none;
        }
        svg{
            display: block;
            margin: auto;
        }
        #editor {  height: 85vh; overflow-y: auto; display: inline}
        #svg_container{ height: 85vh; overflow-y: auto; display: inline}
    </style>
</head>
<body class="orange lighten-5 flexbody">
<jsp:include page="/WEB-INF/views/header_light.jsp"></jsp:include>
<div class="section no-pad-bot" id="index-banner">
    <div class="row ">
        <div class="col s3" id="editor"></div>
        <div class="col s9" id="svg_container"></div>
        <div class="hidden">
            <textarea id="from-text" rows="8" cols="80">
                ${json}
            </textarea>
            <fieldset>
                <legend>Functions</legend>
                <input id="go-button" type="button" value="Visualize">
            </fieldset>

            <fieldset>
                <legend>Show</legend>
                <input id="showArrayNode" type="checkbox" value="showArrayNode" checked>Array Node&nbsp;&nbsp;
                <input id="showLinkName" type="checkbox" value="showLinkName" checked>Link Name&nbsp;&nbsp;
                <input id="showColumn0" type="checkbox" value="showColumn0" checked>Column 1&nbsp;&nbsp;
                <input id="showColumn1" type="checkbox" value="showColumn1" checked>Column 2&nbsp;&nbsp;
            </fieldset>

            <fieldset>
                <legend>Max Length</legend>
                <label>Column 1:
                    <input id="maxNameLen" type="number" min="1" max="256" step="10" size="3" value="32">
                </label>
                <label>Column 2:
                    <input id="maxValueLen" type="number" min="1" max="256" step="10" size="3" value="32">
                </label>
            </fieldset>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    /* set tree*/
    var container = document.getElementById("svg_container");
    var data = document.getElementById("from-text").value;

    var vt = vtree(container)
            .data(data)
            .update();

    function showHandler () {
        vt.conf(this.value, this.checked)
                .update();

        if (this.value === "showColumn0" && this.checked === false) {
            document.getElementById("showColumn1").checked = true;
        } else if (this.value === "showColumn1" && this.checked === false) {
            document.getElementById("showColumn0").checked = true;
        }
    };
    document.getElementById("showArrayNode").onclick = showHandler;
    document.getElementById("showLinkName").onclick = showHandler;
    document.getElementById("showColumn0").onclick = showHandler;
    document.getElementById("showColumn1").onclick = showHandler;


    document.getElementById("go-button").onclick = function () {
        try {
            var v = parseInt(document.getElementById("maxNameLen").value);
            vt.conf("maxNameLen", v);
        } catch (err) {
        }

        try {
            v = parseInt(document.getElementById("maxValueLen").value);
            vt.conf("maxValueLen", v);
        } catch (err) {
        }

        vt.data(document.getElementById("from-text").value);
        vt.update();
    };

    /* set editor*/
    var editor = ace.edit("editor");
    var language_id = ${language_id};
    <%--<% pageContext.setAttribute("newLine", "\n"); %>--%>
    <%-- 현웅아 대소문자에 유의하자>--%>
    <c:set var="newLine" value="<%= \"\n\" %>" />
    var code = '${fn:replace(sourcecode, newLine, "\\n")}';
    editor.setValue(code);
    editor.setTheme("ace/theme/monokai");
    switch (language_id) {
        case 1:
            mode = "ace/mode/c_cpp";
            break;
        case 2:
            mode = "ace/mode/java";
            editor.getSession().setMode("ace/mode/java");
            break;
        case 3:
            mode = "ace/mode/python";
            editor.getSession().setMode("ace/mode/python");
            break;
        default:
            mode = "ace/mode/text";
            break;
    }
    editor.getSession().setMode(mode);
</script>
</html>
