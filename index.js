$(document).ready(function(){

  var t = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn"];
  
  $("a").click(function(){
    var x = t[Math.floor(Math.random()*9)];
    this.attr("data-transition",x);  
  });

});

