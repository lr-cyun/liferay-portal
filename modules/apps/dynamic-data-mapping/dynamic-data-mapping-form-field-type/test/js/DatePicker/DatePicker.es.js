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

import {wait} from '@testing-library/dom';
import {act, cleanup, render} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import {PageProvider} from 'dynamic-data-mapping-form-renderer';
import moment from 'moment';
import React from 'react';

import DatePicker from '../../../src/main/resources/META-INF/resources/DatePicker/DatePicker.es';

const spritemap = 'icons.svg';

const defaultDatePickerConfig = {
	name: 'dateField',
	spritemap,
};

const DatePickerWithProvider = (props) => (
	<PageProvider value={{editingLanguageId: 'en_US'}}>
		<DatePicker {...props} />
	</PageProvider>
);

describe('DatePicker', () => {
	afterEach(cleanup);

	beforeEach(() => {
		jest.useFakeTimers();
		fetch.mockResponseOnce(JSON.stringify({}));
	});

	it('has a helptext', () => {
		const {container} = render(
			<DatePickerWithProvider
				{...defaultDatePickerConfig}
				tip="Type something"
			/>
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('has an id', () => {
		const {container} = render(
			<DatePickerWithProvider {...defaultDatePickerConfig} id="ID" />
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('has a label', () => {
		const {container} = render(
			<DatePickerWithProvider
				{...defaultDatePickerConfig}
				label="label"
			/>
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('has a predefinedValue', () => {
		const {container} = render(
			<DatePickerWithProvider
				{...defaultDatePickerConfig}
				predefinedValue="06/02/2020"
			/>
		);

		act(() => {
			jest.runAllTimers();
		});

		expect(container).toMatchSnapshot();
	});

	it('expands the datepicker when clicking the calendar icon', async () => {
		const {container} = render(
			<DatePickerWithProvider {...defaultDatePickerConfig} />
		);

		userEvent.click(
			container.querySelector('.date-picker-dropdown-toggle')
		);

		act(() => {
			jest.runAllTimers();
		});

		await wait(() =>
			expect(
				document.body.querySelector('.date-picker-dropdown-menu.show')
			).toBeTruthy()
		);
	});

	it('fills the input with the current date selected on Date Picker', async () => {
		const handleFieldEdited = jest.fn();

		const {container, getAllByDisplayValue, getByLabelText} = render(
			<DatePickerWithProvider
				{...defaultDatePickerConfig}
				onChange={handleFieldEdited}
			/>
		);

		userEvent.click(
			container.querySelector('.date-picker-dropdown-toggle')
		);

		act(() => {
			jest.runAllTimers();
		});

		userEvent.click(getByLabelText('Select current date'));

		act(() => {
			jest.runAllTimers();
		});

		await wait(() =>
			expect(
				getAllByDisplayValue(moment().format('MM/DD/YYYY'))
			).toBeTruthy()
		);

		expect(handleFieldEdited).toHaveBeenCalled();
	});
});
