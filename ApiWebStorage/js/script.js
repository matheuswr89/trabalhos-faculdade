$(function () {
    var operacao = "A"; //"A"=Adição; "E"=Edição
    var indice_selecionado = -1;
    var tbGastos = localStorage.getItem("tbGastos");// Recupera os dados armazenados
    var datas;
    tbGastos = JSON.parse(tbGastos); // Converte string para objeto

    if (tbGastos == null) // Caso não haja conteúdo, iniciamos um vetor vazio
        tbGastos = [];

    $("gasto").on("change", function () {
        $(this).val(parseFloat($(this).val()).toFixed(2));
    });

    function Adicionar() {

        var descricao = $("#descricao").val();
        var cliente = JSON.stringify({
            Descricao: descricao,
            Gasto: $("#gasto").val(),
            Data: $("#data").val(),
            Categoria: $("#categoria").val()
        });
        tbGastos.push(cliente);
        localStorage.setItem("tbGastos", JSON.stringify(tbGastos));
        alert("Registro salvo com sucesso!");
        return true;
    }

    function Editar() {
        var data = $("#data").val();
        var cli = JSON.parse(tbGastos[indice_selecionado]);
        let split = data.split('/');
        let formated = split[2] + "-" + split[1] + "-" + split[0];
        tbGastos[indice_selecionado] = JSON.stringify({
            Descricao: $("#descricao").val(),
            Gasto: $("#gasto").val(),
            Data: $("#data").val(),
            Categoria: $("#categoria").val()
        });
        localStorage.setItem("tbGastos", JSON.stringify(tbGastos));
        alert("Registro editado com sucesso!");
        operacao = "A";
        return true;
    }

    function Listar() {
        $("#tblListar").html("");
        $("#tblListar").html(
            "<thead class='text-center'>" +
            "   <tr>" +
            "   <th scope='col'>Descrição</th>" +
            "   <th scope='col'>Gasto</th>" +
            "   <th scope='col'>Data</th>" +
            "   <th scope='col'>Categoria</th>" +
            "   <th scope='col'></th>" +
            "   </tr>" +
            "</thead>" +
            "<tbody>" +
            "</tbody>"
        );
        var soma = 0, soma1 = 0, soma2 = 0, soma3 = 0, soma4 = 0, soma5 = 0, soma6 = 0, soma7 = 0, soma8 = 0, somaData = [];
        var listaData = [], novaArr = [], color = [];;
        for (var i in tbGastos) {
            var cli = JSON.parse(tbGastos[i]);
            var data = cli.Data;
            let split = data.split('-');
            let formated = split[2] + "/" + split[1] + "/" + split[0];
            $("#tblListar tbody").append("<tr class='col-sm-2 text-center'>" +
                "  <td>" + cli.Descricao + "</td>" +
                "  <td>" + cli.Gasto + "</td>" +
                "  <td>" + formated + "</td>" +
                "  <td>" + cli.Categoria + "</td>" +
                "  <td><span alt='" + i + "' class='fa fa-edit btnEditar span'>&nbsp;&nbsp</span><span class='fa fa-window-close btnExcluir span' alt='" + i + "'></span></td>" +
                "</tr>");
            if (cli.Categoria == "Transferência")
                soma1 += parseFloat(cli.Gasto);
            if (cli.Categoria == "Alimentação")
                soma2 += parseFloat(cli.Gasto);
            if (cli.Categoria == "Casa")
                soma3 += parseFloat(cli.Gasto);
            if (cli.Categoria == "Receita Médica")
                soma4 += parseFloat(cli.Gasto);
            if (cli.Categoria == "Saude")
                soma5 += parseFloat(cli.Gasto);
            if (cli.Categoria == "Transporte")
                soma6 += parseFloat(cli.Gasto);
            if (cli.Categoria == "Vestuário")
                soma7 += parseFloat(cli.Gasto);
            if (cli.Categoria == "Outros")
                soma8 += parseFloat(cli.Gasto);

            listaData.push(cli.Data);
            novaArr = listaData.filter((este, i) => listaData.indexOf(este) === i);

            for (var j in novaArr) {
                if (cli.Data == novaArr[j]) {
                    if (!somaData[j]) {
                        somaData[j] = parseFloat(cli.Gasto);
                    } else {
                        somaData[j] += parseFloat(cli.Gasto);
                    }
                }
                //gera uma cor automaticamente
                var letters = '0123456789ABCDEF'.split('');
                color[j] = '#';
                for (var i = 0; i < 6; i++) {
                    color[j] += letters[Math.floor(Math.random() * 16)];
                }
            }

        }

        for (var i in novaArr) {
            var data = novaArr[i];
            let split = data.split('-');
            let formated = split[2] + "/" + split[1] + "/" + split[0];
            novaArr[i] = formated;
        }

        let segundoGrafico = document.getElementById('segundoGrafico').getContext('2d');
        let chart2 = new Chart(segundoGrafico, {
            type: 'bar',
            data: {
                labels: novaArr,
                datasets: [{
                    label: 'Gastos por dia',
                    data: somaData,
                    backgroundColor: color
                }]
            },
           
            options: {
                title: {
                    display: true,
                    text: 'Grafico de Gastos por Dia'
                },
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });

        let primeiroGrafico = document.getElementById('primeiroGrafico').getContext('2d');
        let chart = new Chart(primeiroGrafico, {
            type: 'pie',
            data: {
                labels: ['Transferência', 'Alimentação', 'Casa', 'Receita', 'Saude', 'Transporte', 'Vestuário', 'Outros'],
                datasets: [{
                    label: 'Gastos por Categoria',
                    data: [soma1, soma2, soma3, soma4, soma5, soma6, soma7, soma8],
                    backgroundColor: ["#088A08", "#0404B4", "#ff2200", "#FFFF00", "#3B0B0B", "#58D3F7", "#74DF00", "#0B3B39"]
                }]
            },
            options: {
                title: {
                    display: true,
                    text: 'Grafico de Gastos por Categoria'
                }
            }
        });
    }

    function Excluir() {
        tbGastos.splice(indice_selecionado, 1);
        if (confirm("Deseja excluir o registro?") == true) {
            localStorage.setItem("tbGastos", JSON.stringify(tbGastos));
            location.reload();
            alert("Registro excluido com sucesso!");
        }
        location.reload();
    }

    Listar();

    $("#frmCadastro").on("submit", function () {
        if (operacao == "A") 
            return Adicionar();
         else 
            return Editar();
    });

    $("#frmCadastro").on("reset", function () {
        if (operacao == "A")
            this.reset();
        else {
            this.reset();
            return (operacao = "A");
        }
    });


    $("#index").click(function () {
        $("#home").fadeIn("slow");
    });
    $("#g1").click(function () {
        $("#grafico1").fadeIn("slow");
    });
    $("#g2").click(function () {
        $("#grafico2").fadeIn("slow");
    });

    $("#tblListar").on("click", ".btnEditar", function () {
        operacao = "E";
        indice_selecionado = parseInt($(this).attr("alt"));
        var cli = JSON.parse(tbGastos[indice_selecionado]);
        $("#descricao").val(cli.Descricao);
        $("#gasto").val(cli.Gasto);
        $("#data").val(cli.Data);
        $("#categoria").val(cli.Categoria);
    });

    $("#tblListar").on("click", ".btnExcluir", function () {
        indice_selecionado = parseInt($(this).attr("alt"));
        Excluir();
        Listar();
    });

    $('#button').click(function () {
        $('#jumb').hide();
        $('#home').show();
        $('#grafico1').hide();
        $('#grafico2').hide();
    });

    $('#api').append(function () {
        $('#home').show();
        $('#grafico1').hide();
        $('#grafico2').hide();
    });

    $('#index').click(function () {
        $('#home').show();
        $('#grafico1').hide();
        $('#grafico2').hide();
    });

    $('#g1').click(function () {
        $('#home').hide();
        $('#grafico1').show();
        $('#grafico2').hide();
    });
    $('#g2').click(function () {
        $('#home').hide();
        $('#grafico1').hide();
        $('#grafico2').show();
    });
});
