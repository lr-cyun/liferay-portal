/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {HashRouter, Route, Routes} from 'react-router-dom';
import Layout from '../../layouts/BaseLayout';
import {PAGE_TYPES} from '../../utils/constants';
import Overview from './Overview';
import ProductsOutlet from './ProductsOutlet';
import TeamMembers from './TeamMembers';

const ProjectRoutes = () => {
	return (
		<HashRouter>
			<Routes>
				<Route element={<Layout />} path="/">
					<Route element={<Overview />} index />

					<Route
						element={<ProductsOutlet />}
						path="products/:productId"
					/>

					<Route
						element={<TeamMembers />}
						path={PAGE_TYPES.teamMembers}
					/>

					<Route element={<h3>Page not found</h3>} path="*" />
				</Route>
			</Routes>
		</HashRouter>
	);
};

export default ProjectRoutes;
