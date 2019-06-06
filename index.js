$(document).ready(function(){

  var t = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];

  $("p").click(function(){
     alert(this);
     x = t[Math.floor(Math.random()*10)];
     //this.setAttribute("data-transition",x);
     //alert(this.getAttribute("data-transition"));
     this.next().href = "#"+x;
     alert(this.next().href);
  });

});

