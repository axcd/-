$(document).ready(function(){

  var t = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];
  alert(123);
  var p = $("p");
  alert(p);
  for (var i = 0; i < p.length; i++) {
 
    p[i].click(function(){
    alert(this);
    var a = this.next();
    var x = t[Math.floor(Math.random()*10)];
    //this.setAttribute("data-transition",x);
    //alert(this.getAttribute("data-transition"));
    a.href = "#"+x;
    alert(a);
    alert(a.href);
  });

  };

});

