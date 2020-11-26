
$(document).ready(function() {
	const now = new Date();
	const data = "Acesso: " + zeroFill(now.getUTCDate()) + '/' + zeroFill((now.getMonth() + 1)) + '/' + now.getFullYear();
	const hora = "Horário: " + zeroFill(now.getHours()) + ':' + zeroFill(now.getMinutes()) + ':' + zeroFill(now.getSeconds());
	document.getElementById('data').innerHTML = data;
	document.getElementById('hora').innerHTML = hora;

	$('#staticBackdrop').modal("show");
	mdc.ripple.MDCRipple.attachTo(document.querySelector('.mdc-button'));
	mdc.textField.MDCTextField.attachTo(document.querySelector('.mdc-text-field'));
})

const zeroFill = n => {
	return ('0' + n).slice(-2);
}

const interval = setInterval(() => {
	const now = new Date();
	const data = "Acesso: " + zeroFill(now.getUTCDate()) + '/' + zeroFill((now.getMonth() + 1)) + '/' + now.getFullYear();
	const hora = "Horário: " + zeroFill(now.getHours()) + ':' + zeroFill(now.getMinutes()) + ':' + zeroFill(now.getSeconds());
	document.getElementById('data').innerHTML = data;
	document.getElementById('hora').innerHTML = hora;
}, 1000);

$("#inputName").on("input", function() {
	if ($('#inputName').val() == '') {
		$("#verificado").hide()
		$("#salvar").prop("disabled", true)
		$("#salvar").css("background",  "#BFBFBF")
	} else {
		$("#verificado").show();
		if (checked > 0) {
			$("#salvar").prop("disabled", false)
			$("#salvar").css("background", "#702094")
		}
		else  {
			("#salvar").prop("disabled", true)
			$("#salvar").css("background", "#BFBFBF")
		}
	}
})

let checked = 0

function checkboxAction(checkbox) {
	if (checkbox.checked) {
		checked++;
	} else {
		checked--
	}
	if ($("#verificado").text() == "check" && $("#verificado").css("display") == "block" && checked > 0) {
		$("#salvar").prop("disabled", false)
		$("#salvar").css("background", "#702094")
	}
	else {
		$("#salvar").prop("disabled", true)
		$("#salvar").css("background", "#BFBFBF")
	}
};

$("#veiculos").change(function() {
	$.ajax({
		method: "GET",
		url: '/registro/getproblemas',
		contentType: 'application/json',
		data: { name: $("#veiculos option:selected").val() },
		success: function(response) {
			$("#tabela").html("");

			$("#salvar").prop("disabled", true)
			$("#salvar").css("background", "#BFBFBF")

			checked = 0

			response.forEach((element) => {
				$("#tabela").append(
					"<tr>" +
					"<td>" +
					"<div class=" + "'custom-control'>" +
					"<input onclick='checkboxAction(this)' name=" + "'problema'" + "value='" + element.fkIdProblema + "' type=" + "'checkbox' class='" + "rounded'" + "id='" + element.fkIdProblema + "' >" +
					"</div>" +
					"</td>" +
					"<td>" + element.defeito + "</td>" +
					"<td>" + element.peca + "</td>" +
					"</tr>"
				);
			});
		},
		error: function(jqXhr, textStatus, errorMessage) {
			$("#tabela").html("");
		}
	})
})
