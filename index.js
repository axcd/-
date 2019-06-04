$(document).ready(function(){

  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];

  $("#pagetwo").click(function(){

    $("[href='#pageone']").removeAttr(data-transition);
    $("[href='#pageone']").prop("data-transition",transition[Math.floor(Math.random()*10)]);

  });

$("#pageone").click(function(){

    $("[href='#pagetwo']").removeAttr(data-transition);
    $("[href='#pagetwo']").prop("data-transition",transition[Math.floor(Math.random()*10)]);

  });


});

