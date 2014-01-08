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

	<!-- <a href="help" style="position: absolute; top: 0px; left: 0px;"><img src="resources/images/help.png"/></a> -->

	<div class="container">
		<div class="headline clickable-text">
			<h1 class="main-logo">
				<div class="logo"></div>
				Rento.nu <small>L&auml;ttare hyresl&auml;genheter</small>
			</h1>
		</div>

		<div class="span12">
			<div class="span3">
				<h1>Lediga l&auml;genheter</h1>
				<form class="no-margin" action="/apartments" method="post"
					id="city-form">
					<input id="city-autocomplete" name="city" type="text"
						value="Karlskrona" placeholder="Ort">
				</form>
			</div>
		</div>

		<div class="span12">
			<div id="advanced-settings" class="span6">
				<p class="clickable-text">Fler alternativ</p>
				<div id="advanced-settings-container">
					<p id="room-range">Rum, 1 - 6+</p>
					<div id="room-slider" class="ui-slider"></div>

					<p id="rent-range">Hyra, 0 kr - 20000 kr</p>
					<div id="rent-slider" class="ui-slider"></div>

					<p id="size-range">Storlek, 0 kvm - 200 kvm</p>
					<div id="size-slider" class="ui-slider"></div>

					<input 
						id="student-apartment" 
						type="checkbox" 
						data-toggle="checkbox"
						name="student-apartment" 
						value="student-apartment"
						style="float: left; margin-right: 15px"/>
						
					<p style="float: left;">Inkludera Studentl&auml;genheter</p>

				</div>
			</div>
		</div>

		<div class="span12">
			<table id="apartment-table" class="table table-striped clickable-text"
				>
				<thead>
					<tr>
						<th class="sortable-header" id="mCity">Ort</th>
						<th class="sortable-header" id="mArea">Omr&aring;de</th>
						<th class="sortable-header" id="mAddress">Adress</th>
						<th class="sortable-header" id="mRent">Hyra</th>
						<th class="sortable-header" id="mSize">Storlek</th>
						<th class="sortable-header" id="mRooms">Rum</th>
						<th class="sortable-header" id="mLandlord">Hyresv&auml;rd</th>
						<th class="sortable-header" id="mAdded">Tillagd</th>
					</tr>
				</thead>

				<tbody id="apartment-table-body">

				</tbody>
			</table>

		</div>
	</div>
	<!-- /container -->

	<footer>
		<div class="container">
			<div class="row">
				<div class="span7">
					<h3 class="footer-title" style="padding-left: 5px">Kontakt</h3>
					<p style="padding-left: 5px">tobias@tobiashansson.nu</p>
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
	<script src="resources/js/apartments/functions.js" charset="UTF-8"></script>
	<script src="resources/js/apartments/callbacks.js" charset="UTF-8"></script>
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
					select : function(event, ui) {
						$("#city-autocomplete").val(ui.item.label);
						$("#city-form").submit();
					}
				});
		    });
		});

		$(function() {
			$("#advanced-settings").accordion({
				collapsible : true,
				active : false
			});
		});

		$(function() {
			$("filter-advanced-settings").button().click(function(event) {
				event.preventDefault();
			});
		});

		$(function() {
			$("#room-slider").slider(
					{
						range : true,
						min : 1,
						max : 6,
						values : [ 1, 6 ],
						slide : function(event, ui) {
							var toValue = ui.values[1];
							if (toValue == 6) {
								toValue = '6+';
							}
							$("#room-range").html(
									"Rum, " + ui.values[0] + " - " + toValue);
						},
						stop : function(event, ui) {
							updateFilters();
						}
					});
		});

		$(function() {
			$("#rent-slider").slider(
					{
						range : true,
						min : 0,
						max : 20000,
						values : [ 0, 20000 ],
						step : 100,
						slide : function(event, ui) {
							var toValue = ui.values[1];
							if (toValue == 20000) {
								toValue = '20000+';
							}
							$("#rent-range").html(
									"Hyra, " + ui.values[0] + " kr - "
											+ toValue + " kr");
						},
						stop : function(event, ui) {
							updateFilters();
						}
					});
		});

		$(function() {
			$("#size-slider").slider(
					{
						range : true,
						min : 0,
						max : 200,
						values : [ 0, 200 ],
						step : 5,
						slide : function(event, ui) {
							var toValue = ui.values[1];
							if (toValue == 200) {
								toValue = '200+';
							}
							$("#size-range").html(
									"Storlek, " + ui.values[0] + " kvm - "
											+ toValue + " kvm");
						},
						stop : function(event, ui) {
							updateFilters();
						}
					});
		});
	</script>

</body>
</html>

