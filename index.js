$(document).ready(function(){

  
  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];

  $("[href='#pagetwo']").click(function(){
    $(this).attr("data-transition",transition[Math.floor(Math.random()*10)]);
  });

 $("[href='#pageone']").click(function(){
    $(this).attr("data-transition",transition[Math.floor(Math.random()*10)]);
  });

});

