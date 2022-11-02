const button = document.getElementsByTagName("button")[0];
const input = document.getElementsByTagName("input")[0];
const divResult = document.getElementsByTagName("div")[2];
let coords = { lat: 0, lng: 0 };

button.addEventListener("click", () => {
  inicia();
});

input.onkeydown = (event) => {
  if (event.keyCode === 13) inicia();
};

function inicia() {
  document.getElementById("map").style.display = "none";
  if (input.value != "") {
    acessaApi(
      `http://api.openweathermap.org/data/2.5/weather?q=${input.value}&units=metric&lang=pt_br&APPID=4ae7159844b0c6c2e0e103380226db31&mode=xml`
    );
  } else alert("Preencha os dados corretamente.");
}

function montaResultado(response) {
  var cidade_xml = response.getElementsByTagName('city')[0];
  var temperatura_xml = response.getElementsByTagName('temperature')[0];
        var img_xml =response.getElementsByTagName('weather')[0];
        var sol_xml = response.getElementsByTagName('sun')[0];
        var coordenada_xml = response.getElementsByTagName('coord')[0];
        var altura_nivel_mar_xml = response.getElementsByTagName('pressure')[0];
  let result = "";
  coords = {
    lat: parseFloat(coordenada_xml.getAttribute('lat')),
    lng: parseFloat(coordenada_xml.getAttribute('lon')),
  };
  iniciaMapa();
  result += `
    <h2>${cidade_xml.getAttribute('name')} - ${cidade_xml.getElementsByTagName('country')[0].textContent}</h2>
    <div class="temperatura">
      <div class="tooltip">              
        <p class="temp_atual"><img src="http://openweathermap.org/img/wn/${
          img_xml.getAttribute('icon')
        }@2x.png" /> ${temperatura_xml.getAttribute('value')}°</p>
        <span class="tooltiptext">${img_xml.getAttribute('value')}</span>
      </div>
      <div class="temp_dives">
        <div class="tooltip">          
          <p>${temperatura_xml.getAttribute('max')}°</p><img src="./images/hot.png"/>
            <span class="tooltiptext">Temperatura máxima</span>
        </div>
        <div class="tooltip">
          <p>${temperatura_xml.getAttribute('min')}°</p><img src="./images/cold.png"/>
          <span class="tooltiptext">Temperatura mínima</span>
        </div>
      </div>
    </div>
    <div class="outros">
      <p><b>Nascer do sol:</b> ${date(sol_xml.getAttribute('rise'))}</p>
      <p><b>Pôr do sol:</b> ${date(sol_xml.getAttribute('set'))}</p>
      <p><b>Latitude:</b> ${coords.lat}</p>
      <p><b>Longitude:</b> ${coords.lng}</p>
      ${
        altura_nivel_mar_xml.getAttribute('value') != undefined
          ? `<p><b>Altura em relação ao mar:</b> ${altura_nivel_mar_xml.getAttribute('value')} m</p>`
          : ""
      }
    </div>
  `;
  document.getElementById("map").style.display = "block";
  divResult.innerHTML = result;
}

function iniciaMapa() {
  let map = new google.maps.Map(document.getElementById("map"), {
    center: coords,
    zoom: 10,
  });
  const infowindow = new google.maps.InfoWindow();

  infowindow.setContent(`<b>Você está aqui!</b>`);
  const marker = new google.maps.Marker({ map, position: coords });

  marker.addListener("click", () => {
    infowindow.open(map, marker);
    map.setCenter(coords);
    map.setZoom(10);
  });
}

const date = (timestamp) => {
  let date = new Date(parseInt(timestamp * 1000));
  return date.toLocaleString("pt-BR").split(" ")[1];
};

function acessaApi(url) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4) {
      if (this.status == 200) montaResultado(new DOMParser().parseFromString(this.responseText, "text/xml"));
      else if (this.status == 404)
        divResult.innerHTML = "<h1>Cidade não encontrada!</h1>";
    }
  };
  xhttp.open("GET", url, true);
  xhttp.send();
}
