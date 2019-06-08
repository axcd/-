$(document).ready(function(){

  var t = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn"];
  var x;

  $("a").click(function(){

    x = null;
    x = t[Math.floor(Math.random()*9)];
    this.setAttribute("data-transition",x);  
  });

});

