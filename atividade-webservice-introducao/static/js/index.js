let array = new Array();
let posicao = 0;
let p = document.getElementsByTagName("p")[1];
const inputTurma = document.getElementById("turma");
const inputAno = document.getElementById("ano");
const inputCurso = document.getElementById("curso");
const inputNome = document.getElementById("nome");
const radioSim = document.getElementById("sim");
const radioNao = document.getElementById("nao");
const buttonContinuar = document.getElementsByTagName("button")[0];
const buttonAdicionar = document.getElementsByTagName("button")[1];
const buttonCadastrar = document.getElementsByTagName("button")[2];

if (array.length === 0) buttonCadastrar.style.display = "none";
buttonContinuar.onclick = function () {
  if (
    inputAno.value !== "" &&
    inputTurma.value !== "" &&
    inputCurso.value !== ""
  ) {
    if (parseInt(inputAno.value) <= 2100) {
      adicionaTurma();
      document.getElementsByTagName("div")[1].style.display = "block";
      buttonCadastrar.style.display = "block";
    } else alert("Ano incorreto!");
  } else alert("Preecha os dados corretamente!");
};
buttonAdicionar.onclick = function () {
  adicionaAluno();
  inputNome.focus();
};

function adicionaTurma() {
  let json = {
    turma: inputTurma.value,
    ano: inputAno.value,
    curso: inputCurso.value,
    alunos: [],
  };
  if (array.length === 0) array.push(json);
  let index = verificaSeExiste();
  if (index === undefined) {
    posicao = array.length;
    array.push(json);
  } else posicao = index;
}

function adicionaAluno() {
  if (inputNome.value !== "" && (radioNao.checked || radioSim.checked)) {
    let id = achaUltimoId(array[posicao].alunos);
    array[posicao].alunos.push({
      id: id,
      nome: inputNome.value,
      matriculado: radioNao.checked ? false : true,
    });
    inputNome.value = "";
    radioNao.checked = radioSim.checked = false;
  } else alert("Preecha os dados corretamente!");
}

function achaUltimoId(alunos) {
  if (alunos.length == 0) return 1;
  else return alunos.length + 1;
}

function verificaSeExiste() {
  for (let i = 0; i < array.length; i++) {
    if (
      array[i].turma === inputTurma.value &&
      array[i].ano === inputAno.value &&
      array[i].curso === inputCurso.value
    )
      return i;
  }
}

inputTurma.addEventListener("input", function () {
  document.getElementsByTagName("div")[1].style.display = "none";
});

inputAno.addEventListener("input", function () {
  document.getElementsByTagName("div")[1].style.display = "none";
});

inputCurso.addEventListener("input", function () {
  document.getElementsByTagName("div")[1].style.display = "none";
});

buttonCadastrar.onclick = function () {
  p.innerHTML = "";
  inputAno.value = inputTurma.value = inputCurso.value = "";
  document.getElementsByTagName("div")[1].style.display = "none";
  let socket = io();
  //envia o input do usuário
  socket.emit("send", { array, id: socket.id });
  //recebe o resultado
  socket.on("result", function (data) {
    addMessage(data);
  });
};

function addMessage(message) {
  p.innerHTML = message.toString().replaceAll(",", "");
}

inputNome.onkeyup = function (event) {
  if (event.keyCode == 13) adicionaAluno();
  inputNome.focus();
};
