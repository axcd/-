$(document).ready(function(){

  var t = ["fade", "flip", "flow", "pop", "slide", "slidefade", "slideup", "slidedown", "turn", "none"]; 
    
  $("#fade").click(function(){
    alert(this);
    //var a = this.find("a");
    var x = t[Math.floor(Math.random()*10)];
    a.href = "#"+x;
    //alert(a);
    alert(a.href);
  });

});

