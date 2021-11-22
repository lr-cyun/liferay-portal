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

package com.liferay.frontend.data.set.sample.web.internal.filter;

import com.liferay.frontend.data.set.filter.BaseDateRangeFrontendDataSetFilter;
import com.liferay.frontend.data.set.filter.DateFrontendDataSetFilterItem;
import com.liferay.frontend.data.set.filter.FrontendDataSetFilter;
import com.liferay.frontend.data.set.sample.web.internal.constants.FrontendDataSetSampleFrontendDataSetNames;

import java.util.Calendar;

import org.osgi.service.component.annotations.Component;

/**
 * @author Javier de Arcos
 */
@Component(
	property = "frontend.data.set.name=" + FrontendDataSetSampleFrontendDataSetNames.FRONTEND_DATA_SET_SAMPLES,
	service = FrontendDataSetFilter.class
)
public class SampleDateRangeFrontendDataSetFilter
	extends BaseDateRangeFrontendDataSetFilter {

	@Override
	public String getId() {
		return "date";
	}

	@Override
	public String getLabel() {
		return "Date Range";
	}

	public DateFrontendDataSetFilterItem getMaxDateFrontendDataSetFilterItem() {
		Calendar calendar = Calendar.getInstance();

		return new DateFrontendDataSetFilterItem(
			calendar.get(Calendar.DAY_OF_MONTH),
			calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
	}

	@Override
	public DateFrontendDataSetFilterItem getMinDateFrontendDataSetFilterItem() {
		return new DateFrontendDataSetFilterItem(0, 0, 0);
	}

}