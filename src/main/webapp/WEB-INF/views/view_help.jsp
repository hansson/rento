<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="sv">
<head>

<meta charset="utf-8">
<meta lang="sv">
<meta http-equiv="content-language" content="sv" />
<meta name="language" content="sv" />

<!-- Kill the cache! -->
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />

<title>Rento.nu - L&auml;ttare hyresl&auml;genheter</title>

<!-- Loading Bootstrap -->
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="resources/bootstrap/css/bootstrap-responsive.css"
	rel="stylesheet">

<!-- Loading Flat UI -->
<link href="resources/css/flat-ui.css" rel="stylesheet">

<!-- Rento specific css -->
<link href="resources/css/rento.css" rel="stylesheet">

<link rel="shortcut icon" href="resources/resources/images/favicon.ico">


<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->
</head>

<body>

	<a href="help" style="position: absolute; top: 0px; left: 0px;"><img src="resources/images/help.png"/></a>

	<div class="container">
		<div class="headline clickable-text">
			<h1 class="main-logo">
				<div class="logo"></div>
				Rento.nu <small>L&auml;ttare hyresl&auml;genheter</small>
			</h1>
		</div>
		
		<div class="span12">
			<h1>Hj&auml;lp</h1>
		</div>
		
		<div class="span12">
			<div class="span6" style="margin-left: 10px">
				<p>lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				 lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				  lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				   lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				    lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				     lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				      lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				       lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo</p>
			</div>
			
			<div class="span6" style="margin-left: 10px">
				<p>lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				 lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				  lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				   lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				    lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				     lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				      lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo
				       lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo lo</p>
			</div>
		</div>
		

		<div class="span12">
			<div class="span4">
				<h2>Visa hyresv&auml;rdar f&ouml;r ort</h2>
				<form class="no-margin" action="/apartments" method="post" id="city-form">  
					<input id="city-autocomplete" name="city" type="text"  placeholder="Ort">
				</form>
			</div>
		</div>
		
		<div class="span12">
			<ul id="landlords">
				
			</ul>
		</div>
	</div>
	<!-- /container -->

	<footer>
		<div class="container">
			<div class="row">
				<div class="span7">
					<h3 class="footer-title" style="padding-left: 5px">Kontakt</h3>
					<p style="padding-left: 5px">
						tobias@tobiashansson.nu</p>
				</div>
			</div>
		</div>
	</footer>

	<!-- Load JS here for greater good =============================-->
	<script src="resources/js/jquery-2.0.3.min.js"></script>
	<script src="resources/js/jquery-ui.min.js"></script>
	<!-- <script src="resources/js/jquery.ui.touch-punch.min.js"></script> -->
	<script src="resources/js/jquery.tagsinput.js"></script>
	<script src="resources/js/jquery.placeholder.js"></script>
	<script src="resources/js/jquery.stacktable.js"></script>
	<script src="resources/js/jquery.cookie.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<!-- <script src="resources/js/bootstrap-select.js"></script> -->
	<!--<script src="resources/js/bootstrap-switch.js"></script> -->
	<!--<script src="resources/js/flatui-checkbox.js"></script>-->
	<!--<script src="resources/js/flatui-radio.js"></script> -->
	<!--<script src="resources/js/application.js"></script> -->
	<!-- <script src="resources/js/bootstrap-sortable.js"></script> -->
	<script src="resources/js/help/functions.js" charset="UTF-8"></script>
	<script src="resources/js/help/callbacks.js" charset="UTF-8"></script>
	<script src="resources/js/global/callbacks.js" charset="UTF-8"></script>


	<script type="text/javascript">
	
		$(document).ready(function() {

		});
		
		$(function() {
			$.get('/resources/json/_cities.json',function(data) {
				var availableCities = data.cities;
				$("#city-autocomplete").autocomplete({
					source : availableCities,
					messages : {
						noResults : '',
						results : function() {
						}
					},
					select: function(event, ui) { 
						$("#city-autocomplete").val(ui.item.label);
			            $("#city-form").submit(); 
			        }
				});
			});
		});
	</script>

</body>
</html>

