var client = null;

//Show message index.html
function showMessage(username, text, time){
    let lineBreak = document.createElement('br')
    let newResponse = document.createElement('p');
    newResponse.appendChild(document.createTextNode("Time of sending: " + time + "  "));
    newResponse.appendChild(document.createTextNode("username: " + username + " message: " + text));
    let response = document.getElementById('response');
    response.appendChild(newResponse);
    response.appendChild(lineBreak);
}

function connect(username) {
    client = Stomp.client('ws://localhost:8080/chat');
    client.connect({ username: username, }, function (frame){
        console.log('Web Socket is connected');
        client.subscribe('/users/queue/messages', function(message){
            if (message.body) {
                showMessage(JSON.parse(message.body).username, JSON.parse(message.body).text, JSON.parse(message.body).time);
            } else {
                alert("got empty message");
            }
        });
    });
}

$(function() {
    $("form").on('submit', function(e) {
        e.preventDefault();
    });
    $("#connect").click(function() {
        connect($("#username").val());
    });
    $("#send").click(function() {
        sendMessage();
    });
});

function sendMessage(){
    var text = document.getElementById('text').value;
    var username = document.getElementById('username').value;
    var name = document.getElementById('name').value;

    //Message to send as JSON object
    var quote = {'text':text, 'username':username, 'name': name};

    //EXAMPLE: client.send("/queue/test", {priority: 9}, "Hello, STOMP");
    // The client will send a STOMP SEND frame to /queue/test destination with a header priority set to 9 and a body Hello, STOMP.
    //You can use JSON.stringify to send JSON object instead String

    client.send("/app/chat", {}, JSON.stringify(quote));
}


