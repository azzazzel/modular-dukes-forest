
<%@ include file="/init.jsp" %>

<%
	ProductManager pm = (ProductManager)request.getAttribute("productManager");
	List<Product> products = pm.getAll();
%>


<liferay-ui:search-container
	total="<%= products.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= products %>"
	/>

	<liferay-ui:search-container-row
		className="com.forest.model.Product"
		escapedModel="true"
		modelVar="foo"
	>
		<liferay-ui:search-container-column-text
			name="id"
			property="id"
			valign="top"
		/>

		<liferay-ui:search-container-column-text
			name="name"
			property="name"
			valign="top"
		/>

		<liferay-ui:search-container-column-text
			name="description"
			property="description"
			valign="top"
		/> 

		<liferay-ui:search-container-column-text
			name="price"
			property="price"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

