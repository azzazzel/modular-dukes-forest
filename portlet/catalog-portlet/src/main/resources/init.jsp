
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>

<%@ page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.StringPool"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="javax.portlet.PortletURL"%>
<%@ page import="com.forest.model.Product"%>
<%@ page import="com.forest.usecase.catalog.ProductManager"%>
<%@ page import="java.util.List"%>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	String currentURL = portletURL.toString();
%>