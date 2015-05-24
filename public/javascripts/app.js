var $contactForm = $("#contactForm");

function sendMail() {
    var firstName = $("#name").val();
    var phone = $("#phone").val();
    var email = $("#email").val();
    var message = $("#message").val();
    $.ajax({
        url: "/sendMail",
        type: "POST",
        dataType: "json",
        data: {
            name: firstName,
            phone: phone,
            email: email,
            message: message
        },
        cache: false,
        success: function() {
            // Success message
            var $success = $('#success');
            $success.html("<div class='alert alert-success'>");
            var $success_inner = $success.find('> .alert-success');
            $success_inner.html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                .append("</button>");
            $success_inner
                .append("<strong>Your message has been sent. </strong>");
            $success_inner
                .append('</div>');

            //clear all fields
            $('#contactForm').trigger("reset");
        },
        error: function() {
            // Fail message
            var $success = $('#success');
            $success.html("<div class='alert alert-danger'>");
            var $success_inner = $success.find('> .alert-danger');
            $success_inner.html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                .append("</button>");
            $success_inner.append("<strong>Sorry " + firstName + ", it seems that my mail server is not responding. Please try again later!");
            $success_inner.append('</div>');
            //clear all fields
            $('#contactForm').trigger("reset");
        }
    });
}

$("#searchButton").click(function() {
    $("#query").val($("#searchText").val());
    $("#hidden").submit();
});

$("#searchText").keyup(function(e){
    if(e.keyCode == 13)
    {
        $("#query").val($("#searchText").val());
        $("#hidden").submit();
    }
});

$contactForm.on('submit', function(e) {
    e.preventDefault();
    sendMail();
});

$contactForm.find('#name').focus(function() {
    $('#success').html('');
});