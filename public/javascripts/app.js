$("#searchButton").click(function() {
    $("#q").val($("#searchText").val());
    $("#hidden").submit();
});
$("#searchText").keyup(function(e){
    if(e.keyCode == 13)
    {
        $("#q").val($("#searchText").val());
        $("#hidden").submit();
    }
});