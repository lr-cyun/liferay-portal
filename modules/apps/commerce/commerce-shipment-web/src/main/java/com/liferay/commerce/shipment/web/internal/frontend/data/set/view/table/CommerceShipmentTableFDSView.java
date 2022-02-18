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

package com.liferay.commerce.shipment.web.internal.frontend.data.set.view.table;

import com.liferay.commerce.constants.CommerceShipmentFDSNames;
import com.liferay.frontend.data.set.view.FDSView;
import com.liferay.frontend.data.set.view.table.BaseTableFDSView;
import com.liferay.frontend.data.set.view.table.FDSTableSchema;
import com.liferay.frontend.data.set.view.table.FDSTableSchemaBuilder;
import com.liferay.frontend.data.set.view.table.FDSTableSchemaBuilderFactory;
import com.liferay.frontend.data.set.view.table.FDSTableSchemaField;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Alec Sloan
 */
@Component(
	enabled = false, immediate = true,
	property = "frontend.data.set.name=" + CommerceShipmentFDSNames.SHIPMENTS,
	service = FDSView.class
)
public class CommerceShipmentTableFDSView extends BaseTableFDSView {

	@Override
	public FDSTableSchema getFDSTableSchema(Locale locale) {
		FDSTableSchemaBuilder fdsTableSchemaBuilder =
			_fdsTableSchemaBuilderFactory.create();

		FDSTableSchemaField shipmentIdFDSTableSchemaField =
			fdsTableSchemaBuilder.addFDSTableSchemaField(
				"shipmentId", "shipment-id");

		shipmentIdFDSTableSchemaField.setContentRenderer("actionLink");

		fdsTableSchemaBuilder.addFDSTableSchemaField("accountName", "account");

		fdsTableSchemaBuilder.addFDSTableSchemaField("channelName", "channel");

		fdsTableSchemaBuilder.addFDSTableSchemaField(
			"address", "shipping-address");

		fdsTableSchemaBuilder.addFDSTableSchemaField(
			"tracking", "tracking-number");

		fdsTableSchemaBuilder.addFDSTableSchemaField(
			"createDateString", "create-date");

		fdsTableSchemaBuilder.addFDSTableSchemaField(
			"expectedShipDateString", "estimated-shipping-date");

		fdsTableSchemaBuilder.addFDSTableSchemaField(
			"expectedDeliveryDateString", "estimated-delivery-date");

		FDSTableSchemaField statusFDSTableSchemaField =
			fdsTableSchemaBuilder.addFDSTableSchemaField("status", "status");

		statusFDSTableSchemaField.setContentRenderer("label");

		return fdsTableSchemaBuilder.build();
	}

	@Reference
	private FDSTableSchemaBuilderFactory _fdsTableSchemaBuilderFactory;

}