$(document).ready(function(){

  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];
  
  $("a").click(function(){
    x = transition[Math.floor(Math.random()*10)];
    alert(x);
    $(this).setAttribute("href","#" + x);
    //$(this).href = "#"+x;
    alert($(this).href);
  });

});

