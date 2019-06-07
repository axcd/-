$(document).ready(function(){

  var t = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"]; 
    /*
    $("a").click(function(){
    var x = t[Math.floor(Math.random()*9)];
    this.href = "#"+x;
  });
*/
  
  $("a").click(function(){
    var x = t[Math.floor(Math.random()*9)];
    this.setAttribute("data-transition",x);
    alert(this.getAttribute("data-transition"));
  });

});

