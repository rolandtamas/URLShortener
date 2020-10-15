$('document').ready(function(){
    $('#sendButton').click(function (){
        $.ajax({
            type: 'POST',
            url: '/shortenurl',
            data: JSON.stringify({
                "full_url": $('#URLInput').val()
            }),
            contentType: "application/json; charset=utf-8",
            success: function (data){
                $('#shortURL').val(data.short_url);
            }
        });
    });
});