<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="sv">
<head>

<meta charset="utf-8">
<meta lang="sv">
<meta http-equiv="content-language" content="sv" />
<meta name="language" content="sv" />

<title>Rento - L&auml;ttare hyresl&auml;genheter</title>

<!-- Loading Bootstrap -->
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="resources/bootstrap/css/bootstrap-responsive.css"
	rel="stylesheet">

<!-- Loading Flat UI -->
<link href="resources/css/flat-ui.css" rel="stylesheet">
<link href="resources/css/rento.css" rel="stylesheet">

<link rel="shortcut icon" href="resources/resources/images/favicon.ico">


<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->
</head>

<body>

	<div class="container">
		<div class="headline">
			<h1 class="main-logo">
				<div class="logo"></div>
				Rento <small>L&auml;ttare hyresl&auml;genheter</small>
			</h1>
		</div>

		<div class="span12 no-margin">
			<div class="span3 no-margin">
				<h1>Lediga l&auml;genheter</h1>
				<form class="no-margin" action="/apartments" method="post" id="cityForm">  
					<input id="cityAutocomplete" name="city" type="text" value="" placeholder="Ort">
				</form>
			</div>
		</div>
		
		<div class="span12 no-margin">
			<div id="advancedSettings" class="span6 no-margin">
				<p>Fler alternativ</p>
				<div id="advancedSettingsContainer">
					<p id="roomRange">Rum, 1 - 6+</p>
					<div id="roomSlider" class="ui-slider">
		         	</div>
		         		
		         	<p id="rentRange">Hyra, 0 kr - 20000 kr</p>
					<div id="rentSlider" class="ui-slider">
		         	</div>
		         		
		         	<p id="sizeRange">Storlek, 0 kvm - 200 kvm</p>
					<div id="sizeSlider" class="ui-slider">
		         	</div>
		         	
		         	<button id="filterAdvancedSettings" class="btn btn-primary">Filtrera</button>
		   		</div>
	        </div>
        </div>
		
		<div class="span12 no-margin">
			<table id="apartment-table"class="table table-striped" style="cursor: pointer">
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
	<script src="resources/js/bootstrap-select.js"></script>
	<script src="resources/js/bootstrap-switch.js"></script>
	<!--<script src="resources/js/flatui-checkbox.js"></script>-->
	<!--<script src="resources/js/flatui-radio.js"></script> -->
	<!--<script src="resources/js/application.js"></script> -->
	<script src="resources/js/bootstrap-sortable.js"></script>
	<script src="resources/js/home/functions.js"></script>
	<script src="resources/js/home/callbacks.js"></script>


	<script type="text/javascript">
	
		$(document).ready(function() {

		});
		
		$(function() {
			var availableCities = ${cities};
			$("#cityAutocomplete").autocomplete({
				source : availableCities,
				messages : {
					noResults : '',
					results : function() {
					}
				},
				select: function(event, ui) { 
					$("#cityAutocomplete").val(ui.item.label);
		            $("#cityForm").submit(); 
		        }
			});
		});

		$(function() {
		    $("#advancedSettings").accordion({
		      collapsible: true,
		      active: false
		    });
		});

		$(function() {
		    $("filterAdvancedSettings").button().click(function( event ) {
		        event.preventDefault();
		    });
		});

		$(function() {
		    $("#roomSlider").slider({
				range: true,
			    min: 1,
			    max: 6,
			    values: [1, 6],
			    slide: function( event, ui ) {
				    var toValue = ui.values[1];
				    if(toValue == 6) {
				    	toValue = '6+';
				    }
				    $("#roomRange").html("Rum, " + ui.values[0] + " - " + toValue);
			    },
			    stop: function(event, ui) {
					updateFilters();
				}
		    });
		});

		$(function() {
		    $("#rentSlider").slider({
			    range: true,
			    min: 0,
			    max: 20000,
			    values: [0, 20000],
			    step: 100,
			    slide: function( event, ui ) {
			    	var toValue = ui.values[1];
			    	if(toValue == 20000) {
			    		toValue = '20000+';
			    	}
			      	$("#rentRange").html("Hyra, " + ui.values[0] + " kr - " + toValue + " kr");
			    },
			    stop: function(event, ui) {
					updateFilters();
				}
		    });
		});

		$(function() {
		    $("#sizeSlider").slider({
		    	range: true,
		    	min: 0,
		    	max: 200,
		    	values: [0, 200],
		    	step: 5,
		    	slide: function( event, ui ) {
		    		var toValue = ui.values[1];
		    		if(toValue == 200) {
		    			toValue = '200+';
		    		}
		        	$("#sizeRange").html("Storlek, " + ui.values[0] + " kvm - " + toValue + " kvm");
		      	},
		      	stop: function(event, ui) {
					updateFilters();
				}
		    });
		});
		
	</script>

</body>
</html>

