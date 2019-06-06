$(document).ready(function(){

  var transition = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];
  
  $("div#fade").click(function(){
    alert(this);
    x = transition[Math.floor(Math.random()*10)];
    alert(x);
    //$(this).setAttribute("href","#" + x);
    $(this).find("a")[0].href = "#"+x;
    alert($(this).find("a")[0].href);
  });

});

