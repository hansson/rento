<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<script type="text/javascript">
	var a_orig = ${apartments};
	var a = a_orig.slice(0);
	updateFilters();
</script>


