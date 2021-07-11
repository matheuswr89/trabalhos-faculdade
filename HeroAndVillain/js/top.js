let mybutton = document.getElementById("myBtn");

window.onscroll = function () { scrollFunction() };

function scrollFunction() {
  let body = document.body, html = document.documentElement
  let docHeight = Math.max(body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight);
  let winHeight = window.innerHeight;
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    mybutton.style.display = "block";
    document.getElementById("footer-inicio").style.position = "relative";
  } else {
    mybutton.style.display = "none";
  }
  if (docHeight > winHeight){
      document.getElementById("footer-inicio").style.position = "relative";
    } else {
      document.getElementById("footer-inicio").style.position = "absolute";
    }
}
function topFunction() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}
