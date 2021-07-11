function consultar() {

    var passaValor = function (valor) {
        var nome = valor;
        var element = document.getElementById('element');
        var power = document.getElementById('power');
        var pesquisa = document.getElementById('pesquisa');

        pesquisa.innerHTML = "";
        element.innerHTML = "";

        var request = new XMLHttpRequest();
        if (isNaN(parseInt(nome)) == false) {
            request.open('GET', 'https://superheroapi.com/api.php/2195914800646269/' + nome, true);
            request.onload = function () {

                var data = JSON.parse(this.response);
                console.log(data);
                if (request.status >= 200 && request.status < 400 || data.response == 'success') {
                    element.innerHTML += `<h2>Resultados para "${data.name} - ${nome}"</h2>`;

                    try {
                        pesquisa.innerHTML +=
                            `<button class="collapsible colapse">${data.name}</button>
                                        <div id="content">
                                            <center><img src="${ data.image.url}"class="imgherois"></center> 
                                            Nome:  ${data.name}     
                                            <br> Nome Real:  ${data.biography['full-name']}   
                                            <br>Alter-Ego:  ${data.biography['alter-egos']}  
                                            <br>Aliados:  ${data.biography.aliases}   
                                            <br>Naturalidade:  ${data.biography['place-of-birth']}
                                            <br>Apareceu pela primeira vez em:  ${data.biography['first-appearance']}
                                            <br>Universo:  ${data.biography.publisher}
                                            <br>Do Bem ou do Mau:  ${data.biography.alignment}    
                                            <br>Inteligencia: <div class="porcentagem porc"><div class="por cor porc"  style="width:${data.powerstats.intelligence}%">${data.powerstats.intelligence}</div></div>
                                            <br>Força: <div class="porcentagem porc"><div class="por cor porc"  style="width:${data.powerstats.strength}%">${data.powerstats.strength}</div></div> 
                                            <br>Velocidade: <div class="porcentagem porc"><div class="por cor porc"  style="width:${data.powerstats.speed}%">${data.powerstats.speed}</div></div>
                                            <br>Durabilidade: <div class="porcentagem porc"><div class="por cor porc"  style="width:${data.powerstats.durability}%">${data.powerstats.durability}</div></div> 
                                            <br>Poder: <div class="porcentagem porc"><div class="por cor porc"  style="width:${data.powerstats.power}%">${data.powerstats.power}</div></div>
                                            <br>Combate: <div class="porcentagem porc"><div class="por cor porc"  style="width:${data.powerstats.combat}%"">${data.powerstats.combat}</div></div>   
                                            <br>Genero:  ${data.appearance.gender}    
                                            <br>Raça:  ${data.appearance.race}
                                            <br>Altura:  ${data.appearance.height}   
                                            <br>Peso:  ${data.appearance.weight}
                                            <br>Cor dos Olhos:  ${data.appearance['eye-color']}   
                                            <br>Cor dos Cabelos:  ${data.appearance['hair-color']}    
                                            <br>Oucupação:  ${data.work.occupation}
                                            <br>Base:  ${data.work.base}
                                            <br>Afiliação: ${data.connections['group-affiliation']}
                                            <br>Parentes: ${data.connections.relative}
                                        </div>`;
                        var coll = document.getElementsByClassName("collapsible");
                        console.log(coll);
                        var i;
                        for (i = 0; i < coll.length; i++) {
                            coll[i].addEventListener("click", function () {
                                this.classList.toggle("active");
                                var content = this.nextElementSibling;
                                if (content.style.maxHeight) {
                                    content.style.maxHeight = null;
                                } else {
                                    content.style.maxHeight = content.scrollHeight + "px";
                                }
                            });
                        }
                    } catch (e) {
                        if (data.response == 'error') {
                            alert("O Herói ou Vilão não foi encontrado!!!");
                            element.innerHTML += "";
                        }
                    }
                }
            }
        } else {
            request.open('GET', 'https://superheroapi.com/api.php/2195914800646269/search/' + nome, true);
            request.onload = function () {

                var data = JSON.parse(this.response);
                if (request.status >= 200 && request.status < 400) {
                    element.innerHTML += "<h2>Resultados para " + nome + "</h2>";

                    try {
                        for (var i = 0; i <= data.results.length; i++) {
                            var coll = document.getElementsByClassName("collapsible");
                            for (var i = 0; i < coll.length; i++) {
                                coll[i].addEventListener("click", function () {
                                    this.classList.toggle("active");
                                    var content = this.nextElementSibling;
                                    if (content.style.maxHeight) {
                                        content.style.maxHeight = null;
                                    } else {
                                        content.style.maxHeight = content.scrollHeight + "px";
                                    }
                                });
                            }
                            pesquisa.innerHTML +=
                                `<button class="collapsible colapse">${data.results[i].name}</button>
                                        <div id="content">
                                            <center><img src="${ data.results[i].image.url}"class="imgherois"></center> 
                                            Nome:  ${data.results[i].name}     
                                            <br> Nome Real:  ${data.results[i].biography['full-name']}   
                                            <br>Alter-Ego:  ${data.results[i].biography['alter-egos']}  
                                            <br>Aliados:  ${data.results[i].biography.aliases}   
                                            <br>Naturalidade:  ${data.results[i].biography['place-of-birth']}
                                            <br>Apareceu pela primeira vez em:  ${data.results[i].biography['first-appearance']}
                                            <br>Universo:  ${data.results[i].biography.publisher}
                                            <br>Do Bem ou do Mau:  ${data.results[i].biography.alignment}    
                                            <br>Inteligencia: <div class="porcentagem porc"><div class="por cor porc"  style="width:${data.results[i].powerstats.intelligence}%">${data.results[i].powerstats.intelligence}</div></div>
                                            <br>Força: <div class="porcentagem porc"><div class="por cor porc"  style="width:${data.results[i].powerstats.strength}%">${data.results[i].powerstats.strength}</div></div> 
                                            <br>Velocidade: <div class="porcentagem porc"><div class="por cor porc"  style="width:${data.results[i].powerstats.speed}%">${data.results[i].powerstats.speed}</div></div>
                                            <br>Durabilidade: <div class="porcentagem porc"><div class="por cor porc"  style="width:${data.results[i].powerstats.durability}%">${data.results[i].powerstats.durability}</div></div> 
                                            <br>Poder: <div class="porcentagem porc"><div class="por cor porc"  style="width:${data.results[i].powerstats.power}%">${data.results[i].powerstats.power}</div></div>
                                            <br>Combate: <div class="porcentagem porc"><div class="por cor porc"  style="width:${data.results[i].powerstats.combat}%"">${data.results[i].powerstats.combat}</div></div>   
                                            <br>Genero:  ${data.results[i].appearance.gender}    
                                            <br>Raça:  ${data.results[i].appearance.race}
                                            <br>Altura:  ${data.results[i].appearance.height}   
                                            <br>Peso:  ${data.results[i].appearance.weight}
                                            <br>Cor dos Olhos:  ${data.results[i].appearance['eye-color']}   
                                            <br>Cor dos Cabelos:  ${data.results[i].appearance['hair-color']}    
                                            <br>Oucupação:  ${data.results[i].work.occupation}
                                            <br>Base:  ${data.results[i].work.base}
                                            <br>Afiliação: ${data.results[i].connections['group-affiliation']}
                                            <br>Parentes: ${data.results[i].connections.relative}
                                        </div>`;
                        }
                    } catch (e) {
                        if (data.results == null) {
                            alert("O Herói ou Vilão não foi encontrado!!!");
                            element.innerHTML += "";
                        }
                    }
                }
            }
        }
        request.send();
    }
    passaValor(document.getElementById('nomeHeroi').value);
}

document.addEventListener('keydown', function (event) {
    if (event.keyCode !== 13) return;
    var passaValor = function (valor) {
        consultar();
    }
    passaValor(document.getElementById('nomeHeroi').value);
});
