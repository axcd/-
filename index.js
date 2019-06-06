$(document).ready(function(){

  var t = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];
  var a = $("a");

  for (i = 0; i < a.length; i++) {

    alert(a.length);

    a[i].onmouseover(function(){
      alert(this);
      x = t[Math.floor(Math.random()*10)];
      //this.setAttribute("data-transition",x);
      //alert(this.getAttribute("data-transition"));
      this.href = "#"+x;
      alert(this.href);
    });

  };

});

