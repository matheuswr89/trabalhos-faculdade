const url = "https://api.github.com/users/";
let json = {};
let p = document.getElementsByTagName("p")[0];
let button = document.getElementsByTagName("button")[0];
let input = document.getElementsByTagName("input")[0];

button.onclick = async function () {
  await inicia();
};

input.addEventListener("keyup", async function (e) {
  if (e.keyCode == 13) await inicia();
});

async function inicia() {
  p.innerHTML = `<p>Carregando....</p><img src="loading.gif">`;
  if (localStorage.getItem(input.value) === null)
    await pesquisaUser(input.value);
  else json = JSON.parse(localStorage.getItem(input.value));
  if (json) mostraResultados();
}

async function pesquisaUser(user) {
  json = null;
  let result = await acessaApi(url + user + "/followers");
  console.log(result);
  if (result === null) {
    p.innerText = "Rate limit da API esgotado.";
    return;
  }
  if (result.message === "Not Found") {
    p.innerText = "Usuário não encontrado.";
    return;
  }
  json = { usuario: user, quant_followers: result.length, followers: [] };
  for (let i = 0; i < result.length; i++) {
    json.followers.push({
      nome: await pegaNomeCompleto(result[i].login),
      login: result[i].login,
      repositorios: await pegaRepositoriosUser(result[i].login),
    });
  }
  localStorage.setItem(user, JSON.stringify(json));
}

async function pegaNomeCompleto(user) {
  let result = await acessaApi(url + user);
  return result.name === null ? "-" : result.name;
}

async function pegaRepositoriosUser(user) {
  let array = [];
  let result = await acessaApi(url + user + "/repos");
  for (let i = 0; i < result.length; i++) {
    array.push({ name: result[i].name, url: result[i].html_url });
  }
  return array;
}

function mostraResultados() {
  let result = `<p>Usuario: ${json.usuario}</p><p>${
    json.quant_followers !== undefined
      ? json.quant_followers
      : "O perfil não tem"
  } seguidores:</p>`;
  let followers = json.followers;
  for (let i = 0; i < followers.length; i++) {
    result += `<p class="user" onclick="mudarEstado(${i})"><b>${
      followers[i].nome === "-" ? "(Sem nome)" : followers[i].nome
    } - ${followers[i].login}</b></p>
        <div id="${i}" style="display: none;">`;
    let repos = followers[i].repositorios;
    for (let j = 0; j < repos.length; j++) {
      result += `<a class="repos" href="${repos[j].url}" target="_blank">${repos[j].name}</a><br/>`;
    }
    result += `</div>`;
  }
  p.innerHTML = result;
}

async function acessaApi(url) {
  const response = await fetch(url);
  if (response.status === 403) return null;
  return await response.json();
}

function mudarEstado(el) {
  var display = document.getElementById(el).style.display;
  if (display == "none") document.getElementById(el).style.display = "block";
  else document.getElementById(el).style.display = "none";
}
