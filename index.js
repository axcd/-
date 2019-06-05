$(document).ready(function(){

  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];

  //$("div#pagetwo").click(function(){

    //$("[href='#pagetwo']").attr("data-transition",transition[Math.floor(Math.random()*10)]);
    //$("[href='#pagetwo']").removeAttr("data-transition");

  //});

//$("div#pageone").click(function(){

    //$("[href='#pageone']").attr("data-transition",transition[Math.floor(Math.random()*10)]);
    //$("[href='#pageone']").removeAttr("data-transition");

  //});
  
  $("a").click(function(){
  
    $("a").css("herf","#"+ transition[Math.floor(Math.random()*10)]);

  });


});

