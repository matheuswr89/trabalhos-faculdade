const express = require("express");
const app = express();
const socketio = require("socket.io");

app.use(express.static(__dirname + "/public"));

const expressServer = app.listen(3000);
const io = socketio(expressServer);

let phraseResponse = "";
//cria a conexão
io.on("connection", function (socket) {
  //recebe o input do usuário
  socket.on("send", (response) => {
    let input = response.input;
    if (input !== undefined) {
      let splitInput = input.split(";");
      splitInput.pop();
      let classWithMoreStudent = moreStudent(splitInput);
      let oldestYear = oldest(splitInput);
      let average = averageQuantity(splitInput);
      phraseResponse = `
      <p>Quantidade de turmas: ${splitInput.length}</p>
      <p>Nome da turma com mais alunos: ${classWithMoreStudent}</p>
      <p>Ano da turma mais antiga: ${oldestYear}</p>
      <p>Quantidade média de alunos por turma: ${average}</p>
      `;
    }
  });
  //envia o resultado
  socket.emit("result", { message: phraseResponse, id: socket.id });
});

function moreStudent(splitInput) {
  let bigger = 0,
    position = -1;
  for (let i = 0; i < splitInput.length; i++) {
    let students = splitInput[i].split("#")[2].split(",").length;
    if (students > bigger) {
      bigger = students;
      position = i;
    }
  }
  return splitInput[position].split("#")[0];
}

function oldest(splitInput) {
  let old = 10000;
  for (let i = 0; i < splitInput.length; i++) {
    let year = parseInt(splitInput[i].split("#")[1]);
    if (year < old) {
      old = year;
    }
  }
  return old;
}

function averageQuantity(splitInput) {
  let sum = 0;
  for (let i = 0; i < splitInput.length; i++) {
    sum += splitInput[i].split("#")[2].split(",").length;
  }
  return sum / splitInput.length;
}
