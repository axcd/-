$(document).ready(function(){

  var t = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn"];
  var x = null;

  $("a").click(function(){
    x = t[Math.floor(Math.random()*9)];
    this.setAttribute("data-transitio",x);  
    alert(x);
  });

  $("p").click(function(){
    x = t[Math.floor(Math.random()*9)]; alert(this);
    var a = this.lastChild("a"); alert(2);
    a.setAttribute("data-transition",x); alert(2);
    window.location.href="https://www.baidu.com"; //axcd.github.io/mao/"+this.find("a").href;
  });

});

