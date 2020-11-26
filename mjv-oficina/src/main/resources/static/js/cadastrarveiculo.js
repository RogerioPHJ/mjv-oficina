$(document).ready(function() {
	$('#staticBackdrop').modal("show");
	mdc.ripple.MDCRipple.attachTo(document.querySelector('.mdc-button'));
	mdc.textField.MDCTextField.attachTo(document.querySelector('.mdc-text-field'));
	mdc.form-field.MDCFormField.attachTo(document.querySelector('.mdc-form-field'));
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
})