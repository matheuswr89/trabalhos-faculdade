let array = new Array();
let posicao = 0;
const inputTurma = document.getElementById("turma");
const inputAno = document.getElementById("ano");
const inputNome = document.getElementById("nome");
const buttonContinuar = document.getElementsByTagName("button")[0];
const buttonAdicionar = document.getElementsByTagName("button")[1];
const buttonCadastrar = document.getElementsByTagName("button")[2];

if (array.length === 0) buttonCadastrar.style.display = "none";
buttonContinuar.onclick = function () {
  if (inputAno.value !== "" && inputTurma.value !== "") {
    if (parseInt(inputAno.value) <= 2100) {
      adicionaTurma();
      document.getElementsByTagName("div")[1].style.display = "block";
      buttonCadastrar.style.display = "block";
    } else alert("Ano incorreto!");
  }
};
buttonAdicionar.onclick = function () {
  adicionaAluno();
  inputNome.focus();
};

function adicionaTurma() {
  if (array.length === 0) array.push([inputTurma.value, inputAno.value, []]);
  let index = verificaSeExiste();
  if (index === undefined) {
    posicao = array.length;
    array.push([inputTurma.value, inputAno.value, []]);
  } else posicao = index;
}

function adicionaAluno() {
  if (inputNome.value !== "") {
    array[posicao][2].push(inputNome.value);
    inputNome.value = "";
  }
}

function verificaSeExiste() {
  for (let i = 0; i < array.length; i++) {
    if (array[i][0] === inputTurma.value && array[i][1] === inputAno.value)
      return i;
  }
}

inputTurma.addEventListener("input", function () {
  document.getElementsByTagName("div")[1].style.display = "none";
});

inputAno.addEventListener("input", function () {
  document.getElementsByTagName("div")[1].style.display = "none";
});

function montaString() {
  let conteudo = "";
  for (var i = 0; i < array.length; i++) {
    conteudo +=
      array[i][0] + "#" + array[i][1] + "#[" + array[i][2].toString() + "];";
  }
  return conteudo;
}

buttonCadastrar.onclick = function () {
  inputAno.value = inputTurma.value = "";
  document.getElementsByTagName("div")[1].style.display = "none";
  let socket = io();
  let conteudo = montaString();
  //envia o input do usuário
  socket.emit("send", { input: conteudo, id: socket.id });
  //recebe o resultado
  socket.on("result", function (data) {
    addMessage(data.message);
  });
  socket.on("error", console.error.bind(console));
  socket.on("message", console.log.bind(console));
};

function addMessage(message) {
  let p = document.getElementsByTagName("p")[1];
  p.innerHTML = message;
}

inputNome.onkeyup = function (event) {
  if (event.keyCode == 13) adicionaAluno();
  inputNome.focus();
};
