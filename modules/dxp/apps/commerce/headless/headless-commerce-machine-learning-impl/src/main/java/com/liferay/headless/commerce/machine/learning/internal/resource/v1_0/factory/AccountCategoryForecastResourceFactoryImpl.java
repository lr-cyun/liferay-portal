/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.headless.commerce.machine.learning.internal.resource.v1_0.factory;

import com.liferay.headless.commerce.machine.learning.internal.security.permission.LiberalPermissionChecker;
import com.liferay.headless.commerce.machine.learning.resource.v1_0.AccountCategoryForecastResource;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.odata.filter.ExpressionConvert;
import com.liferay.portal.odata.filter.FilterParserProvider;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Riccardo Ferrari
 * @generated
 */
@Component(
	enabled = false, immediate = true,
	service = AccountCategoryForecastResource.Factory.class
)
@Generated("")
public class AccountCategoryForecastResourceFactoryImpl
	implements AccountCategoryForecastResource.Factory {

	@Override
	public AccountCategoryForecastResource.Builder create() {
		return new AccountCategoryForecastResource.Builder() {

			@Override
			public AccountCategoryForecastResource build() {
				if (_user == null) {
					throw new IllegalArgumentException("User is not set");
				}

				return _accountCategoryForecastResourceProxyProviderFunction.
					apply(
						(proxy, method, arguments) -> _invoke(
							method, arguments, _checkPermissions,
							_httpServletRequest, _httpServletResponse,
							_preferredLocale, _user));
			}

			@Override
			public AccountCategoryForecastResource.Builder checkPermissions(
				boolean checkPermissions) {

				_checkPermissions = checkPermissions;

				return this;
			}

			@Override
			public AccountCategoryForecastResource.Builder httpServletRequest(
				HttpServletRequest httpServletRequest) {

				_httpServletRequest = httpServletRequest;

				return this;
			}

			@Override
			public AccountCategoryForecastResource.Builder httpServletResponse(
				HttpServletResponse httpServletResponse) {

				_httpServletResponse = httpServletResponse;

				return this;
			}

			@Override
			public AccountCategoryForecastResource.Builder preferredLocale(
				Locale preferredLocale) {

				_preferredLocale = preferredLocale;

				return this;
			}

			@Override
			public AccountCategoryForecastResource.Builder user(User user) {
				_user = user;

				return this;
			}

			private boolean _checkPermissions = true;
			private HttpServletRequest _httpServletRequest;
			private HttpServletResponse _httpServletResponse;
			private Locale _preferredLocale;
			private User _user;

		};
	}

	@Activate
	protected void activate() {
		AccountCategoryForecastResource.FactoryHolder.factory = this;
	}

	@Deactivate
	protected void deactivate() {
		AccountCategoryForecastResource.FactoryHolder.factory = null;
	}

	private static Function<InvocationHandler, AccountCategoryForecastResource>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			AccountCategoryForecastResource.class.getClassLoader(),
			AccountCategoryForecastResource.class);

		try {
			Constructor<AccountCategoryForecastResource> constructor =
				(Constructor<AccountCategoryForecastResource>)
					proxyClass.getConstructor(InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private Object _invoke(
			Method method, Object[] arguments, boolean checkPermissions,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Locale preferredLocale,
			User user)
		throws Throwable {

		String name = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(user.getUserId());

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (checkPermissions) {
			PermissionThreadLocal.setPermissionChecker(
				_defaultPermissionCheckerFactory.create(user));
		}
		else {
			PermissionThreadLocal.setPermissionChecker(
				new LiberalPermissionChecker(user));
		}

		AccountCategoryForecastResource accountCategoryForecastResource =
			_componentServiceObjects.getService();

		accountCategoryForecastResource.setContextAcceptLanguage(
			new AcceptLanguageImpl(httpServletRequest, preferredLocale, user));

		Company company = _companyLocalService.getCompany(user.getCompanyId());

		accountCategoryForecastResource.setContextCompany(company);

		accountCategoryForecastResource.setContextHttpServletRequest(
			httpServletRequest);
		accountCategoryForecastResource.setContextHttpServletResponse(
			httpServletResponse);
		accountCategoryForecastResource.setContextUser(user);
		accountCategoryForecastResource.setExpressionConvert(
			_expressionConvert);
		accountCategoryForecastResource.setFilterParserProvider(
			_filterParserProvider);
		accountCategoryForecastResource.setGroupLocalService(
			_groupLocalService);
		accountCategoryForecastResource.setResourceActionLocalService(
			_resourceActionLocalService);
		accountCategoryForecastResource.setResourcePermissionLocalService(
			_resourcePermissionLocalService);
		accountCategoryForecastResource.setRoleLocalService(_roleLocalService);

		try {
			return method.invoke(accountCategoryForecastResource, arguments);
		}
		catch (InvocationTargetException invocationTargetException) {
			throw invocationTargetException.getTargetException();
		}
		finally {
			_componentServiceObjects.ungetService(
				accountCategoryForecastResource);

			PrincipalThreadLocal.setName(name);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
	}

	private static final Function
		<InvocationHandler, AccountCategoryForecastResource>
			_accountCategoryForecastResourceProxyProviderFunction =
				_getProxyProviderFunction();

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AccountCategoryForecastResource>
		_componentServiceObjects;

	@Reference
	private PermissionCheckerFactory _defaultPermissionCheckerFactory;

	@Reference(
		target = "(result.class.name=com.liferay.portal.kernel.search.filter.Filter)"
	)
	private ExpressionConvert<Filter> _expressionConvert;

	@Reference
	private FilterParserProvider _filterParserProvider;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

	private class AcceptLanguageImpl implements AcceptLanguage {

		public AcceptLanguageImpl(
			HttpServletRequest httpServletRequest, Locale preferredLocale,
			User user) {

			_httpServletRequest = httpServletRequest;
			_preferredLocale = preferredLocale;
			_user = user;
		}

		@Override
		public List<Locale> getLocales() {
			return Arrays.asList(getPreferredLocale());
		}

		@Override
		public String getPreferredLanguageId() {
			return LocaleUtil.toLanguageId(getPreferredLocale());
		}

		@Override
		public Locale getPreferredLocale() {
			if (_preferredLocale != null) {
				return _preferredLocale;
			}

			if (_httpServletRequest != null) {
				Locale locale = (Locale)_httpServletRequest.getAttribute(
					WebKeys.LOCALE);

				if (locale != null) {
					return locale;
				}
			}

			return _user.getLocale();
		}

		@Override
		public boolean isAcceptAllLanguages() {
			return false;
		}

		private final HttpServletRequest _httpServletRequest;
		private final Locale _preferredLocale;
		private final User _user;

	}

}