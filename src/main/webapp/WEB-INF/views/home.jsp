<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="sv">
<head>

<meta charset="utf-8">
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

		<div class="span3 span-no-margin">
			<h1>Lediga l&auml;genheter</h1>
			<form action="/apartments" method="post" id="city_form">  
				<input id="cityAutocomplete" name="city" type="text" value="" placeholder="Ort">
			</form>
		</div>
		
		<div class="span12 span-no-margin">
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
	<script src="resources/js/jquery.ui.touch-punch.min.js"></script>
	<script src="resources/js/jquery.tagsinput.js"></script>
	<script src="resources/js/jquery.placeholder.js"></script>
	<script src="resources/js/jquery.stacktable.js"></script>
	<script src="resources/js/jquery.cookie.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/bootstrap-select.js"></script>
	<script src="resources/js/bootstrap-switch.js"></script>
	<script src="resources/js/flatui-checkbox.js"></script>
	<script src="resources/js/flatui-radio.js"></script>
	<script src="resources/js/application.js"></script>
	<script src="resources/js/bootstrap-sortable.js"></script>


	<script type="text/javascript">
		
		$(document).ready(function() {

		});
		
		function reloadApartments() {
			var r = new Array(), j = -1;
			for (var key = 0, size = a.length; key < size; key++) {
				r[++j] = '<tr class="apartment" style="cursor: pointer" data="';
			    r[++j] = a[key].mUrl;
			    r[++j] = '"><td>';
				r[++j] = a[key].mCity;
				r[++j] = '</td><td>';
				r[++j] = a[key].mArea;
				r[++j] = '</td><td>';
				r[++j] = a[key].mAddress;
				r[++j] = '</td><td>';
				r[++j] = a[key].mRent;
				r[++j] = 'kr</td><td>';
				r[++j] = a[key].mSize;
				r[++j] = 'kvm</td><td>';
				r[++j] = a[key].mRooms;
				r[++j] = '</td><td>';
				r[++j] = a[key].mLandlord;
				r[++j] = '</td></tr>';
			}
			$('#apartment-table-body').html(r.join(''));
			
			$(".apartment").on("click", function() {
				window.open($(this).attr('data'));
				return false;
			});
		}
		
		function sortApartments(prop, asc) {
			$.cookie('sorted', prop);
			$.cookie('sorted-asc', asc);
		    a = a.sort(function(a, b) {
		    	var first = a[prop];
		    	var second = b[prop];
		    	if(first.indexOf('&') !== -1) {
		    		first = replaceSpecialCharacters(first);
		    	}
		    	
				if(second.indexOf('&') !== -1) {
					second = replaceSpecialCharacters(second);
		    	}
		    	
		        if (asc) {
		        	return (first > second) ? 1 : ((first < second) ? -1 : 0);
		        } else {
		        	return (second > first) ? 1 : ((second < first) ? -1 : 0);
		        } 
		    });
		    reloadApartments();
		}
		
		function replaceSpecialCharacters(replaceString) {
			replaceString = replaceString.replace('&aring;', 'å');
			replaceString = replaceString.replace('&auml;', 'ä');
			replaceString = replaceString.replace('&ouml;', 'ö');
			replaceString = replaceString.replace('&Aring;', 'Å');
			replaceString = replaceString.replace('&Auml;', 'Ä');
			replaceString = replaceString.replace('&Ouml;', 'Ö');
			return replaceString;
		}
		
		$(function() {
			var availableCities = ${cities};
			$("#cityAutocomplete").autocomplete({
				source : availableCities,
				messages : {
					noResults : '',
					results : function() {
					}
				}
			});
		});
		
		$('#apartment-table-body').load('/apartments');
		
		$('#city_form').on('submit', function(event) {

		    var link = $(this).attr('action');

		    $.post(link,$(this).serialize(),function(data, status) {
		    	$('#apartment-table-body').html(data);
		    });

		    return false; // dont post it automatically
		});
		
		$('.sortable-header').on('click', function(event) {
			var id = $(this).attr('id');
			var cookie = $.cookie('sorted');
			if(!cookie || cookie != id) {
				sortApartments(id, true);
			} else {
				var asc = $.cookie('sorted-asc');
				if(asc == "true") {
					sortApartments(id, false);
				} else {
					sortApartments(id, true);
				}
			}
		});
	</script>

</body>
</html>

