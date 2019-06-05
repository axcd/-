$(document).ready(function(){

  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];

  $("div#pagetwo").click(function(){

    $("[href='#pageone']").prop("data-transition",transition[Math.floor(Math.random()*10)]);
    $("[href='#pagetwo']").removeProp("data-transition");

  });

$("div#pageone").click(function(){

    $("[href='#pagetwo']").prop("data-transition",transition[Math.floor(Math.random()*10)]);
    $("[href='#pageone']").removeProp("data-transition");

  });


});

