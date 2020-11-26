$(document).ready(function() {
	$('#staticBackdrop').modal("show");
	mdc.ripple.MDCRipple.attachTo(document.querySelector('.mdc-button'));
	mdc.textField.MDCTextField.attachTo(document.querySelector('.mdc-text-field'));
})
