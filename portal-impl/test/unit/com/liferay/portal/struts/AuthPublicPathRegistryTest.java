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

package com.liferay.portal.struts;

import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Leon Chi
 */
public class AuthPublicPathRegistryTest {

	@Test
	public void testContains() {
		RegistryUtil.setRegistry(new BasicRegistryImpl());

		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<Object> serviceRegistration =
			registry.registerService(
				Object.class, new Object(),
				new HashMap<String, Object>() {
					{
						put("auth.public.path", _TEST_AUTH_PUBLIC_PATH);
					}
				});

		try {
			Assert.assertTrue(
				AuthPublicPathRegistry.contains(_TEST_AUTH_PUBLIC_PATH));
			Assert.assertFalse(
				AuthPublicPathRegistry.contains("/unregistered/unknown/path"));
		}
		finally {
			serviceRegistration.unregister();
		}
	}

	private static final String _TEST_AUTH_PUBLIC_PATH =
		"TEST_AUTH_PUBLIC_PATH";

}