$(document).ready(function(){

  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];

  $("[data-role='content']").click(function(){

    $("[href='#pageone']").removeAttr(data-transition);
    $("[href='#pageone']").prop("data-transition",transition[Math.floor(Math.random()*10)]);

  });

$("[data-role='content']").click(function(){

    $("[href='#pagetwo']").removeAttr(data-transition);
    $("[href='#pagetwo']").prop("data-transition",transition[Math.floor(Math.random()*10)]);

  });


});

