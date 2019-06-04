$(document).ready(function(){

  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];
  var x;

  $("a").click(function(){
    $(this).attr("data-transition",transition[Math.floor(Math.random()*10)]);
  });

});

