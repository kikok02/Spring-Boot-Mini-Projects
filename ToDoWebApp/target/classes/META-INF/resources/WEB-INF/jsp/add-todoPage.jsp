
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>


<div class="container">
	<h1>Enter ToDo Details</h1>
	<form:form method="post" modelAttribute="todo">

		<fieldset class="mb-3">
			<form:label path="description">Description: </form:label>
			<form:input type="text" path="description" required="required" />
			<form:errors path="description" cssClass="text-warning" />
		</fieldset>

		<fieldset class="mb-3">
			<form:label path="targetDate">Target Date: </form:label>
			<form:input id="targetDate" type="text" path="targetDate" />
			<form:errors path="targetDate" cssClass="text-warning" />
		</fieldset>

		<fieldset class="mb-3">
			<form:label path="done">Done: </form:label>
			<form:input type="text" path="done" />
			<form:errors path="done" cssClass="text-warning" />
		</fieldset>


		<form:input type="hidden" path="id" />
		<input type="submit" value="Add" class="btn btn-success">
	</form:form>
</div>

<%@ include file="common/footer.jspf"%>

<script type="text/javascript">
	$('#targetDate').datepicker({
		format : 'yyyy-mm-dd'
	});
</script>

</body>
</html>