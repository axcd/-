$(document).ready(function(){

  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];

  $("a").click(function(){
    window.alert(transition[Math.floor(Math.random()*10)]) ;
    $("a").attr("data-transition",transition[Math.floor(Math.random()*10)]);
  });

});

