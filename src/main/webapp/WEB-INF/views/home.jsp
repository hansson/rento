<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="sv">
<head>

<meta charset="utf-8">
<title>Rento - L&auml;ttare hyresl&auml;genheter</title>

<!-- Loading Bootstrap -->
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

<!-- Loading Flat UI -->
<link href="resources/css/flat-ui.css" rel="stylesheet">

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

		<h1>Lediga l&auml;genheter</h1>
		<input type="text" value="" placeholder="Ort" class="span3">

		<div class="span12" style="margin: 0px">
			<table class="table table-striped" style="cursor: pointer">
				<thead>
					<tr>
						<th>Ort</th>
						<th>Omr&aring;de</th>
						<th>Adress</th>
						<th>Hyra</th>
						<th>Storlek</th>
						<th>Rum</th>
						<th>Hyresv&auml;rd</th>
					</tr>
				</thead>

				<tbody id="apartment-table">
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
					<p style="padding-left: 5px">Fr&aring;gor och f&ouml;rb&auml;ttringsf&ouml;rslag kan skickas till tobias@tobiashansson.nu</p>

				</div>
				<!-- /span8 -->

			</div>
		</div>
	</footer>

	<!-- Load JS here for greater good =============================-->
	<script src="resources/js/jquery-2.0.3.min.js"></script>
	<script src="resources/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="resources/js/jquery.ui.touch-punch.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/bootstrap-select.js"></script>
	<script src="resources/js/bootstrap-switch.js"></script>
	<script src="resources/js/flatui-checkbox.js"></script>
	<script src="resources/js/flatui-radio.js"></script>
	<script src="resources/js/jquery.tagsinput.js"></script>
	<script src="resources/js/jquery.placeholder.js"></script>
	<script src="resources/js/jquery.stacktable.js"></script>
	<script src="http://vjs.zencdn.net/c/video.js"></script>
	<script src="resources/js/application.js"></script>
	<script src="resources/js/bootstrap-sortable.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$(".apartment").on("click", function() {
				window.open($(this).attr('data'));
				return false;
			});
		});
	</script>

	<script type="text/javascript">
		var a = ${apartments};

		var r = new Array(), j = -1;
		for ( var key = 0, size = a.length; key < size; key++) {
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
		$('#apartment-table').html(r.join(''));
	</script>

</body>
</html>

