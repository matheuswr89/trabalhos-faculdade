document.onkeyup=function(e){
	if(e.which == 13){
		buscaPersonagem();
	}
}
async function buscaPersonagem(){
	let string = "";
	const personagem = document.querySelector('input').value;
	if (personagem){
		let url = `https://gateway.marvel.com/v1/public/characters?ts=1&apikey=71d809c51844971410df166458717c08&hash=2bedf8d545ff19fa42ec46ffe7711a8a&nameStartsWith=${personagem}`;
		let message = document.getElementById("message");
		message.innerHTML = "Carregando...";
		const response = await acessaApi(url);
		message.innerHTML = "";
		if(response.data.total===0){
			message.innerHTML = `Nenhum resultado para ${personagem}`;
			string = "";
		}else{
			string+=`<div class="card-container">`;
			for (let i=0; i<response.data.results.length;i++){
				let element = response.data.results[i];
				string+=`
				<div class="card" id="${element.id}">
				<img class="img-card" src="${element.thumbnail.path}.${element.thumbnail.extension}">
				<header class="article-header">
				<h2 class="article-title">
				${element.name}
				</h2>
				</header>
				<button class="fonte-button" onclick="iniciaModal(${element.id})">
				<a>Saiba mais</a>
				</button>
				</div>
				`;
			}
			string+=`</div>`;
		}
		card.innerHTML = string;
	} else{
		alert("Digite um nome!!")
	}
}

async function iniciaModal(id) {
	var modal = document.getElementById("myModal");
	var span = document.getElementsByClassName("close")[0];
	modal.style.display = "block";
	document.getElementById("carregando").innerHTML = "Carregando....";
	span.onclick = function() {
		modal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}

	let url = `https://gateway.marvel.com/v1/public/characters/${id}?ts=1&apikey=71d809c51844971410df166458717c08&hash=2bedf8d545ff19fa42ec46ffe7711a8a`;
	document.getElementById("title").innerHTML = "";                      
	document.getElementById("imagem-personagem").innerHTML = "";
	document.getElementById("descricao").innerHTML = "";
	document.getElementById("botao-visit").innerHTML = "";
	document.getElementById("comics-relacionado").innerHTML = "Carregando....";
	document.getElementById("series-relacionado").innerHTML = "Carregando....";

	const response = await acessaApi(url);
	let element = response.data.results[0];                              
	document.getElementById("title").innerHTML = element.name;
	document.getElementById("carregando").innerHTML = "";
	document.getElementById("imagem-personagem").innerHTML = `<img class="imagem" src="${element.thumbnail.path}.${element.thumbnail.extension}">`;
	if(element.description){
		document.getElementById("descricao").innerHTML = `<h2>Descrição:</h2> ${element.description}`;
	}
	else{
		document.getElementById("descricao").innerHTML = "<h2>Descrição:</h2><p>Sem descricao.</p>"
	}

	document.getElementById("botao-visit").innerHTML = `<button class="botao-visit" ><a href="${element.urls[0].url}">Visitar página na Marvel</a></button>`;
	comicsRelacionado(element.comics);
	seriesRelacionado(element.series);
}

async function comicsRelacionado(json){
	if(json){
		let stringComic = "";
		document.getElementById("relacionados").innerHTML=`<h2>Comics relacionados:</h2>`;
		if(json.items.length){
			stringComic+=`<ul id="comic-rel" class="caroussel-ul">`;
			for(let i =0; i<json.items.length;i++){
				let comicItem = json.items[i];
				let urlComic = `${comicItem.resourceURI}?ts=1&apikey=71d809c51844971410df166458717c08&hash=2bedf8d545ff19fa42ec46ffe7711a8a`;
				let urlReplace = urlComic.replace('http', 'https');
				const comicItens = await acessaApi(urlReplace);

				let comic = comicItens.data.results[0]; 
				stringComic+=`
				<li id="comic-rel-li" class="card caroussel-li">
				<img class="img-card" src="${comic.thumbnail.path}.${comic.thumbnail.extension}">
				<header class="article-header">
				<h2 class="article-title">
				${comic.title}
				</h2>
				</header>
				<button class="fonte-button">
				<a href="${comic.urls[0].url}">Saiba mais</a>
				</button>

				</li>
				`;
			}
			if(json.available > 3){
				stringComic+=`
				</ul>
				<button class="prev" onclick="show(-2)">&lt;</button>
				<button class="next" onclick="show(+2)">&gt;</button>`;
			}else{
				stringComic+=`</ul>`;
			}
		}else{
			stringComic+=`<p>Nenhum comic relacionado</p>`;
		}
		document.getElementById("comics-relacionado").innerHTML=stringComic;
		document.getElementById("carregando").innerHTML = "";
		let liEls = document.querySelectorAll('#comic-rel #comic-rel-li');
		let index = 0;
		window.show = function(increase) {
			index = index + increase;
			index = Math.min(
				Math.max(index,0),
				liEls.length-1
				);
			liEls[index].scrollIntoView({behavior: 'smooth'});
		}
	}
}


async function seriesRelacionado(json){
	if(json){
		let stringSerie = "";
		document.getElementById("seriesrelacionados").innerHTML=`<h2>Series relacionados:</h2>`;
		if(json.items.length){
			stringSerie+=`<ul id="serie-rel" class="caroussel-ul">`;
			for(let i =0; i<json.items.length;i++){
				let serieItem = json.items[i];
				let urlSerie = `${serieItem.resourceURI}?ts=1&apikey=71d809c51844971410df166458717c08&hash=2bedf8d545ff19fa42ec46ffe7711a8a`;
				let urlReplace = urlSerie.replace('http', 'https');
				const seriesItens = await acessaApi(urlReplace);

				let serie = seriesItens.data.results[0];
				let thumbnail;
				if(serie.thumbnail){
					thumbnail = `${serie.thumbnail.path}.${serie.thumbnail.extension}`;
				} else{
					thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg";
				}
				stringSerie+=`
				<li id="serie-rel-li" class="card caroussel-li">
				<img class="img-card" src="${thumbnail}">
				<header class="article-header">
				<h2 class="article-title">
				${serie.title}
				</h2>
				</header>
				<button class="fonte-button">
				<a href="${serie.urls[0].url}">Saiba mais</a>
				</button>

				</li>
				`;
			}
			if(json.available > 3){
				stringSerie+=`
				</ul>
				<button class="prev" onclick="showSerie(-2)">&lt;</button>
				<button class="next" onclick="showSerie(+2)">&gt;</button>`;
			}else{
				stringSerie+=`</ul>`;
			}
		}else{
			stringSerie+=`<p>Nenhuma serie relacionada</p>`;
		}
		document.getElementById("series-relacionado").innerHTML=stringSerie;
		document.getElementById("carregando").innerHTML = "";
		let liEls = document.querySelectorAll('#serie-rel #serie-rel-li');
		let index = 0;
		window.showSerie = function(increase) {
			index = index + increase;
			index = Math.min(
				Math.max(index,0),
				liEls.length-1
				);
			liEls[index].scrollIntoView({behavior: 'smooth'});
		}
	}
}

async function acessaApi(url){
	const response = await fetch(url);
	const json = await response.json();
	return json;
}