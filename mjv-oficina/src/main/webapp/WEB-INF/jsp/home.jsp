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
	href="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script
	src="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
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
<link rel="stylesheet" href="/css/home.css">
</head>
<body>
	<script type="text/javascript" src="/js/home.js"></script>
	<!-- Modal -->
	<div class="modal" id="staticBackdrop" data-backdrop="static"
		data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<h2 class="text-center">Sistema da oficina da MJV!</h2>
					<div class="row">
						<div class="col-1"></div>
						<a class="col-4 my-2" href="/registro/cadastro"><button
								class="mdc-button px-3 w-100 mdc-button--raised">
								<span class="mdc-button__label">Registro de defeitos
									veiculares</span>
							</button></a>
						<div class="col-1"></div>
						<div class="col-1"></div>
						<a class="col-4 my-2" href="/veiculo"><button
								class="mdc-button px-3 w-100 mdc-button--raised">
								<span class="mdc-button__label">Cadastrar tipo de veículo</span>
							</button></a>
						<div class="col-1"></div>
						<div class="col-1"></div>
						<a class="col-4 my-2" href="/peca"><button
								class="mdc-button px-3 w-100 mdc-button--raised">
								<span class="mdc-button__label">Cadastrar peças</span>
							</button></a>
						<div class="col-1"></div>
						<div class="col-1"></div>
						<a class="col-4 my-2" href="/defeito"><button
								class="mdc-button px-3 w-100 mdc-button--raised">
								<span class="mdc-button__label">Cadastrar defeitos</span>
							</button></a>
						<div class="col-1"></div>
						<div class="col-12 row m-0">
							<div class="col-4"></div>
							<a class="col-4 my-2" href="/registro/consulta"><button
									class="mdc-button px-3 w-100 mdc-button--raised">
									<span class="mdc-button__label">Consultar Registros</span>
								</button></a>
							<div class="col-4"></div>
						</div>

					</div>

				</div>
			</div>
		</div>
	</div>

</body>
</html>