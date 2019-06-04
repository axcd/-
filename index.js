$(document).ready(function(){

  $("[href='#pagetwo']").click(function(){
    $("[href='#pageone']").attr("data-transition","flip");
  });

 $("[href='#pageone']").click(function(){
    $("[href='#pagetwo']").attr("data-transition","flip");
  });

});
