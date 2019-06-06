$(document).ready(function(){

  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];
  
  $("div").click(function(){
    alert(this);
    x = transition[Math.floor(Math.random()*10)];
    alert(x);
    $(this).setAttribute("href","#" + x);
    //$(this).href = "#"+x;
    alert($(this).href);
  });

});

