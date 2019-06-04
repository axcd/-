$(document).ready(function(){

  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];
  var x;

  $($("[data-role='content']")).click(function(){
    $("[href='#pageone']").removeAttr(data-transition);
    $("[href='#pageone']").prop("data-transition",transition[Math.floor(Math.random()*10)]);
  });

});

