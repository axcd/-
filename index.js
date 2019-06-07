$(document).ready(function(){

  var t = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn"];
  
  $("a").focus(function(){
    var x = t[Math.floor(Math.random()*9)];
    this.setAttribute("data-transition",x);  
  });

});

