$(document).ready(function() {
	$('#staticBackdrop').modal("show");
	mdc.ripple.MDCRipple.attachTo(document.querySelector('.mdc-button'));
	mdc.textField.MDCTextField.attachTo(document.querySelector('.data-incio'));
	mdc.textField.MDCTextField.attachTo(document.querySelector('.data-fim'));
})

function ajaxDetalhes(button) {
	console.log($(".list" + button.id))

	$.ajax({
		method: "GET",
		url: '/registro/getpecadefeito',
		contentType: 'application/json',
		data: { id: button.id},
		success: function(response) {
			$(".list" + button.id).html("");

			response.forEach((element) => {
				$(".list" + button.id).append(
					'<li class="list-group-item listDefeitoPeca ml-4 pl-3">' + element.nomePeca + ' - ' + element.nomeDefeito + '</li>'
				);
			});
		},
		error: function(jqXhr, textStatus, errorMessage) {
		}
	})
}

function pesquisar() {
	console.log($("#nameVeiculo option:selected").val())
	console.log($("#dataInicio").val())
	console.log($("#dataFim").val())

	$.ajax({
		method: "GET",
		url: '/registro/consultar',
		contentType: 'application/json',
		data: { nameVeiculo: $("#nameVeiculo option:selected").val(), dataInicio: $("#dataInicio").val(), dataFim: $("#dataFim").val() },
		success: function(response) {
			$("#accordionExample").html("");
			response.forEach((registro) => {

				$("#accordionExample").append(
					'<div class="card">' +
					'<div class="card-header p-0" id="heading' + registro.idRegistro + '">' +
					'<div class="row">' +
					'<div class="col-1"></div>' +
					'<div class="col-3 py-3 registroColumn">' + registro.nomeCliente +
					'</div>' +
					'<div class="col-2 py-3 registroColumn">' + registro.data +
					'</div>' +
					'<div class="col-3 py-3 registroColumn">' + registro.nomeVeiculo +
					'</div>' +
					'<div class="col-3 py-3">' +
					'<button onclick="ajaxDetalhes(this)" id="' + registro.idRegistro + '" type="button" style="text-decoration: underline !important; color:#FF7800; border:none; line-height: 1.3rem" class="btn btn-link btn-block text-left registroColumn" data-toggle="collapse" data-target="#collapse' + registro.idRegistro + '" aria-expanded="true" aria-controls="collapse' + registro.idRegistro + '">' +
					'Ver detalhes' +
					'</button>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'<div id="collapse' + registro.idRegistro + '" class="collapse" aria-labelledby="heading' + registro.idRegistro + '" data-parent="#accordionExample">' +
					'<div class="card-body">' +
					'<ul class="list-group list' + registro.idRegistro + '">' +

					'</ul>' +
					'</div>' +
					'</div>' +
					'</div>'
				);
			});
		},
		error: function(jqXhr, textStatus, errorMessage) {
		}
	}) 
}
