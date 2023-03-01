<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  	<script src="https://code.jquery.com/jquery-3.6.2.js"></script>

</head>
<body>
<input type="text" id="text"/>
<button id="toTTS" type="button">To TTS</button>
  <audio id="audioContainer"> <!-- audio play를 위함 -->
        <source id="audioSource" src="" /> <!-- audio 파일 -->
        Your browser does not support the audio format.
      </audio>
</body>
<script>
    $("#toTTS").click(function (){
        $.ajax({
            type : "post",
            url : "/tts",
            data : {text : $("#text").val()},
        })
        .done(function(response){
            new Audio("data:audio/wav;base64," + response).play();
        })
        .fail(function(e){
            alert(e);
        });
    })
</script>
</html>