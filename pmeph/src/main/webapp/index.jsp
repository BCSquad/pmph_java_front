<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Web Socket JavaScript Echo Client</title>
    <script src="https://cdn.bootcss.com/sockjs-client/1.0.0/sockjs.min.js"></script>
    <script language="javascript" type="text/javascript">
        var echo_websocket;
        echo_websocket = new SockJS("http://localhost:8080/springws/websocket") ;   //初始化 websocket

        echo_websocket.onopen = function () {
            console.log('Info: connection opened.');
        };

        echo_websocket.onmessage = function (event) {
            console.log('Received: ' + event.data); //处理服务端返回消息
        };
        echo_websocket.onclose = function (event) {
            console.log('Info: connection closed.');
            console.log(event);
        };
        function init() {
            output = document.getElementById("output");
        }
        function send_echo() {
            doSend("I have send the message");
        }

        function doSend(message) {
            echo_websocket.send(message);
            writeToScreen("Sent message: " + message);
        }
        function writeToScreen(message) {
            var pre = document.createElement("p");
            pre.style.wordWrap = "break-word";
            pre.innerHTML = message;
            output.appendChild(pre);
        }
        window.addEventListener("load", init, false);
    </script>
</head>
<body>
<h1>Echo Server</h1>
<div style="text-align: left;">
    <form action="">
        <input onclick="send_echo()" value="send websocket request" type="button">
        <input id="textID" name="message" value="Hello world, Web Sockets" type="text">
        <br>
    </form>
</div>
<div id="output"></div>
</body>
</html>
