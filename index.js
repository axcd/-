$(document).ready(function(){

  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];

  $("div#pagetwo").click(function(){

    $("[href='#pageone']").removeAttr(data-transition);
    $("[href='#pageone']").attr("data-transition",transition[Math.floor(Math.random()*10)]);

  });

$("div#pageone").click(function(){

    $("[href='#pagetwo']").removeAttr(data-transition);
    $("[href='#pagetwo']").attr("data-transition",transition[Math.floor(Math.random()*10)]);

  });


});

