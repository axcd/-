$(document).ready(function(){
  $("[href='#pagetwo']").click(function(){
    $("[href='#pageone']").attr("data-transition",'"flip"');
  });
});
