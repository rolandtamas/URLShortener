$('document').ready(function(){
    $('#sendButton').click(function (){
        $.ajax({
            type: 'POST',
            url: 'localhost:8080/shortenurl',
            data: JSON.stringify({
                "full_url": $('#URLInput').val()
            }),
            contentType: "application/json; charset=utf-8",
            success:function (data){
                $('#shortURL').val(data.shortURL);
            }
        });
    });
});