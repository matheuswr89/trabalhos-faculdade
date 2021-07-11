document.onkeyup=function(e){
	if(e.which == 13){
		buscaCommic();
	}
}
async function buscaCommic(){
	let string = "";
	const commic = document.querySelector('input').value;
	if (commic){
		let url = `https://gateway.marvel.com/v1/public/comics?ts=1&apikey=71d809c51844971410df166458717c08&hash=2bedf8d545ff19fa42ec46ffe7711a8a&titleStartsWith=${commic}`;
		let message = document.getElementById("message");
		message.innerHTML = "Carregando...";
		const response = await acessaApi(url);
		message.innerHTML = "";
		if(response.data.total===0){
			message.innerHTML = `Nenhum resultado para ${commic}`;
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
				${element.title}
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

	let url = `https://gateway.marvel.com/v1/public/comics/${id}?ts=1&apikey=71d809c51844971410df166458717c08&hash=2bedf8d545ff19fa42ec46ffe7711a8a`;
	document.getElementById("title").innerHTML = "";                      
	document.getElementById("imagem-personagem").innerHTML = "";
	document.getElementById("descricao").innerHTML = "";
	document.getElementById("botao-visit").innerHTML = "";
	document.getElementById("comics-relacionado").innerHTML = "Carregando....";
	document.getElementById("series-relacionado").innerHTML = "Carregando....";
	const response = await acessaApi(url);
	let element = response.data.results[0];                              
	document.getElementById("title").innerHTML = element.title;
	document.getElementById("carregando").innerHTML = "";
	document.getElementById("imagem-personagem").innerHTML = `<img class="imagem" src="${element.thumbnail.path}.${element.thumbnail.extension}">`;
	if(element.description){
		document.getElementById("descricao").innerHTML = `<h2>Descrição:</h2> ${element.description}`;
	}else{
		document.getElementById("descricao").innerHTML = "<h2>Descrição:</h2><p>Sem descricao.</p>"
	}

	document.getElementById("botao-visit").innerHTML = `<button class="botao-visit" ><a href="${element.urls[0].url}">Visitar página na Marvel</a></button>`;
	personagensRelacionados(element.characters);
	seriesRelacionado(element.series)
}

async function personagensRelacionados(json){
	let stringCharacters = "";
	document.getElementById("relacionados").innerHTML=`<h2>Personagem relacionados:</h2>`;
	if(json.items.length){
		stringCharacters+=`<ul class="caroussel-ul">`;
		for(let i =0; i<json.items.length;i++){
			let charactersItem = json.items[i];
			let urlComic = `${charactersItem.resourceURI}?ts=1&apikey=71d809c51844971410df166458717c08&hash=2bedf8d545ff19fa42ec46ffe7711a8a`;
			let urlReplace = urlComic.replace('http', 'https');
			const charactersItens = await acessaApi(urlReplace);

			let characters = charactersItens.data.results[0]; 
			stringCharacters+=`
			<li class="card caroussel-li">
			<img class="img-card" src="${characters.thumbnail.path}.${characters.thumbnail.extension}">
			<header class="article-header">
			<h2 class="article-title">
			${characters.name}
			</h2>
			</header>
			<button class="fonte-button">
			<a href="${characters.urls[0].url}">Saiba mais</a>
			</button>

			</li>
			`;
		}
		if(json.available > 3){
			stringCharacters+=`
				</ul>
				<button class="prev" onclick="show(-2)">&lt;</button>
				<button class="next" onclick="show(+2)">&gt;</button>`;
		}else{
			stringCharacters+=`</ul>`;
		}
	}else{
		stringCharacters+=`<p>Nenhum personagem relacionado</p>`;
	}
	document.getElementById("comics-relacionado").innerHTML=stringCharacters;
	document.getElementById("carregando").innerHTML = "";
	let liEls = document.querySelectorAll('.caroussel-ul .caroussel-li');
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

async function seriesRelacionado(json){
	let stringSerie = "";
	if(json){
		document.getElementById("seriesrelacionados").innerHTML=`<h2>Series relacionados:</h2>`;
		if(json){
			stringSerie+=`<ul id="serie-rel" class="caroussel-ul">`;

			let urlSerie = `${json.resourceURI}?ts=1&apikey=71d809c51844971410df166458717c08&hash=2bedf8d545ff19fa42ec46ffe7711a8a`;
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
		stringSerie+=`</ul>`;
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


async function acessaApi(url){
	const response = await fetch(url);
	const json = await response.json();
	return json;
}