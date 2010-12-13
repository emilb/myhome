<%@tag import="se.greyzone.myhome.web.action.BaseAction"%>
<%@tag import="se.greyzone.myhome.web.action.context.SiteArea"%>
<%@ tag pageEncoding="UTF-8" %>

<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ attribute name="pageRef" required="true"%>
<%@ attribute name="name" required="true"%>
<%@ attribute name="siteArea" required="true"%>


<%
	BaseAction actionBean = (BaseAction)request.getAttribute("actionBean");
	String selectedCssClass = " class=\"current_page_item\"";
	String currCssClass = "";
	if (actionBean.isSiteArea(siteArea)) {
		currCssClass = selectedCssClass;
	}
%>

			<li<%=currCssClass%>><a href="${pageContext.request.contextPath}/${pageRef}">${name}</a></li>