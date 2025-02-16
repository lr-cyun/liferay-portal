<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
Layout selLayout = layoutsAdminDisplayContext.getSelLayout();

UnicodeProperties layoutTypeSettings = selLayout.getTypeSettingsProperties();
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="advanced"
/>

<aui:model-context bean="<%= selLayout %>" model="<%= Layout.class %>" />

<liferay-ui:error exception="<%= ImageTypeException.class %>" message="please-enter-a-file-with-a-valid-file-type" />

<%
Group group = layoutsAdminDisplayContext.getGroup();
%>

<c:if test="<%= !group.isLayoutPrototype() %>">
	<div class="alert alert-warning layout-prototype-info-message <%= selLayout.isLayoutPrototypeLinkActive() ? StringPool.BLANK : "hide" %>">
		<liferay-ui:message arguments='<%= new String[] {LanguageUtil.get(request, "inherit-changes"), "General"} %>' key="some-page-settings-are-unavailable-because-x-is-enabled" translateArguments="<%= false %>" />
	</div>

	<aui:input cssClass="propagatable-field" disabled="<%= selLayout.isLayoutPrototypeLinkActive() %>" helpMessage="query-string-help" label="query-string" name="TypeSettingsProperties--query-string--" size="30" type="text" value='<%= GetterUtil.getString(layoutTypeSettings.getProperty("query-string")) %>' />
</c:if>

<%
String targetType = GetterUtil.getString(layoutTypeSettings.getProperty("targetType"));
%>

<aui:select cssClass="propagatable-field" id="targetType" label="target-type" name="TypeSettingsProperties--targetType--">
	<aui:option label="open-page-in-a-specific-frame" selected='<%= !Objects.equals(targetType, "useNewTab") %>' value="" />
	<aui:option label="open-page-in-a-new-tab" selected='<%= Objects.equals(targetType, "useNewTab") %>' value="useNewTab" />
</aui:select>

<aui:input cssClass="propagatable-field" disabled="<%= selLayout.isLayoutPrototypeLinkActive() %>" id="target" label="target" name="TypeSettingsProperties--target--" size="15" type="text" value='<%= GetterUtil.getString(layoutTypeSettings.getProperty("target")) %>' wrapperCssClass='<%= Objects.equals(targetType, "useNewTab") ? "hide" : "" %>' />

<aui:field-wrapper helpMessage="this-icon-will-be-shown-in-the-navigation-menu" label="icon" name="iconFileName">
	<liferay-ui:logo-selector
		currentLogoURL='<%= (selLayout.getIconImageId() == 0) ? themeDisplay.getPathThemeImages() + "/spacer.png" : themeDisplay.getPathImage() + "/logo?img_id=" + selLayout.getIconImageId() + "&t=" + WebServerServletTokenUtil.getToken(selLayout.getIconImageId()) %>'
		defaultLogo="<%= selLayout.getIconImageId() == 0 %>"
		defaultLogoURL='<%= themeDisplay.getPathThemeImages() + "/spacer.png" %>'
		tempImageFileName="<%= String.valueOf(selLayout.getPlid()) %>"
	/>
</aui:field-wrapper>

<liferay-frontend:component
	componentId='<%= liferayPortletResponse.getNamespace() + "addLayout" %>'
	module="js/Advanced"
/>