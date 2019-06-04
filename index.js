$(document).ready(function(){

  $("[href='#pagetwo']").click(function(){
    $("[href='#pagetwo']").attr("data-transition","flip");
  });

 $("[href='#pageone']").click(function(){
    $(this).attr("data-transition","flip");
  });

});
