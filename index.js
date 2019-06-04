$(document).ready(function(){

  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];
  var x;

  $("a").click(function(){
    x = transition[Math.floor(Math.random()*10)];
    window.alert(x) ;
    $("a").attr("data-transition",x);
  });

});

