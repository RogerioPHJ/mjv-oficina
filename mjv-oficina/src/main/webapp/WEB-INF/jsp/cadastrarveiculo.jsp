<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MJV Oficina</title>
<!-- Material Design -->
<link
	rel="stylesheet"
	href="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script
	src="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>
<!-- Fontes -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Baloo+2:wght@400;700&family=Catamaran:wght@400;700&display=swap"
	rel="stylesheet">
<!-- CSS da Página -->
<link rel="stylesheet" href="/css/cadastrarveiculo.css">
</head>
<body>
	
	<!-- Modal -->
	<div class="modal" id="staticBackdrop" data-backdrop="static"
		data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form method="post" action="/veiculo/cadastrar">
					<div class="modal-header d-block px-5 border-0">
						<h3 class="my-4 w-100">Cadastrar tipo de veículo</h3>
						<div class="mb-2">
							<label
								class="w-100 mdc-text-field mdc-text-field--outlined mdc-text-field--with-leading-icon">
								<span class="mdc-notched-outline"> <span
									class="mdc-notched-outline__leading"></span> <span
									class="mdc-notched-outline__notch"> <span
										class="mdc-floating-label" id="my-label-id">Digite aqui
											o tipo de veículo</span>
								</span> <span class="mdc-notched-outline__trailing"></span>
							</span> <i
								class="material-icons mdc-text-field__icon mdc-text-field__icon--leading"
								tabindex="0" role="button">history_edu</i> <input name="nome"
								id="inputName" class="mdc-text-field__input" type="text"
								aria-labelledby="my-label-id"> <i id="verificado"
								style="display: none"
								class="material-icons mdc-text-field__icon mdc-text-field__icon--trailing"
								tabindex="0" role="button">check</i>
							</label>
						</div>
					</div>
					<div class="modal-body px-5">
						<div class="row pb-4">
							<div class="mdc-form-field col-12 row">
								<div class="col-12 ml-3 pl-0"
									style="border-bottom: 1px black solid; background: #f0f0f0">
									<div class="mdc-checkbox ml-3" style="margin: 10px 0"></div>
									<label class="ml-5"
										style="margin-bottom: 20px; font-weight: 700; font-size: 18px">Peças</label>
								</div>
								<c:forEach items="${ pecasList }" var="peca">
									<div class="col-12 ml-3 pl-0"
										style="border-bottom: 1px black solid; background: #f0f0f0">
										<div class="mdc-checkbox ml-3" style="margin: 20px 0">
											<input type="checkbox" class="mdc-checkbox__native-control"
												id="<c:out value="${ peca.idPeca }"></c:out>"
												value="<c:out value="${ peca.nome }"></c:out>" name="peca" />
											<div class="mdc-checkbox__background rounded-circle">
												<svg class="mdc-checkbox__checkmark" viewBox="0 0 24 24">
							        <path class="mdc-checkbox__checkmark-path" fill="none"
														d="M1.73,12.91 8.1,19.28 22.79,4.59" />
							      </svg>
												<div class="mdc-checkbox__mixedmark"></div>
											</div>
											<div class="mdc-checkbox__ripple"></div>
										</div>
										<label class="ml-5"
											for="<c:out value="${ peca.idPeca }"></c:out>"
											style="margin-bottom: 30px"><c:out
												value="${ peca.nome }"></c:out></label>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="modal-footer border-0 row pb-4 pr-5">
						<a class="col-6" href="/">
							<button class="mdc-button" type="button" style="color: #702094">
								<div class="mdc-button__ripple"></div>
								<i class="material-icons mdc-button__icon" aria-hidden="true">arrow_back</i>
								<span class="mdc-button__label">Voltar ao menu</span>
							</button>
						</a>
						<button id="salvar" type="submit"
							class="mdc-button col-5 px-3 w-100 mdc-button--raised"
							style="background: #BFBFBF; border-radius: 20px">
							<span class="mdc-button__label">Salvar</span>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

<script>
$(document).ready(function() {
	$('#staticBackdrop').modal("show");
	mdc.ripple.MDCRipple.attachTo(document.querySelector('.mdc-button'));
	mdc.textField.MDCTextField.attachTo(document.querySelector('.mdc-text-field'));
	mdc.formfield.MDCFormField.attachTo(document.querySelector('.mdc-form-field'));
	mdc.checkbox.MDCCheckbox.attachTo(document.querySelector('.mdc-checkbox'));
})

let checked = 0

$(".mdc-checkbox__native-control").change(function() {
	if (this.checked) {
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
});

$("#inputName").on("input", function() {

	if ($('#inputName').val() == '') {
		$("#verificado").css("display", "none")
		$("#salvar").prop("disabled", true)
		$("#salvar").css("background", "#BFBFBF")
		return;
	}

	$.ajax({
		method: "GET",
		url: '/veiculo/checkname',
		contentType: 'application/json',
		data: { name: $("#inputName").val() },
		success: function(data, textStatus, xhr) {
			console.log("success")
			$("#verificado").text("close")
			$("#verificado").css("display", "block")
			$("#salvar").prop("disabled", true)
			$("#salvar").css("background", "#BFBFBF")
		},
		error: function(jqXhr, textStatus, errorMessage) {
			console.log("erro")
			$("#verificado").text("check")
			$("#verificado").css("display", "block")
			if (checked > 0) {
				$("#salvar").prop("disabled", false)
				$("#salvar").css("background", "#702094")
			}

		}
	})
})</script>
</body>
</html>