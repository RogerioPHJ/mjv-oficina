$(document).ready(function() {
	$('#staticBackdrop').modal("show");
	mdc.ripple.MDCRipple.attachTo(document.querySelector('.mdc-button'));
	mdc.textField.MDCTextField.attachTo(document.querySelector('.mdc-text-field'));
})

$("#inputName").on("input", function() {

	if ($('#inputName').val() == '') {
		$("#vericado").css("display", "none")
		$("#salvar").prop("disabled", true)
		$("#salvar").css("background", "#BFBFBF")
		return;
	}

	$.ajax({
		method: "GET",
		url: '/defeito/checkname',
		contentType: 'application/json',
		data: { name: $("#inputName").val() },
		success: function(data, textStatus, xhr) {
			$("#vericado").text("close")
			$("#vericado").css("display", "block")
			$("#salvar").prop("disabled", true)
			$("#salvar").css("background", "#BFBFBF")
		},
		error: function(jqXhr, textStatus, errorMessage) {
			$("#vericado").text("check")
			$("#vericado").css("display", "block")
			$("#salvar").prop("disabled", false)
			$("#salvar").css("background", "#702094")
		}
	})
});