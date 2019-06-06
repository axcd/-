$(document).ready(function(){

  var t = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"];
  var a = $("a");
  alert(a.length);

  for (i = 0; i < a.length; i++) {

    a[i].click(function(){
      alert(this);
      x = t[Math.floor(Math.random()*10)];
      alert(x);
      //a[i].setAttribute("href","#" + x);
      a[i].href = "#"+x;
      alert(a[i].href);
    });

  };

});

