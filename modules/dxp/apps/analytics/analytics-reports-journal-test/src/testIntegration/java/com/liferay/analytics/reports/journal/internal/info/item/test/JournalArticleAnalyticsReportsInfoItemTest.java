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

package com.liferay.analytics.reports.journal.internal.info.item.test;

import com.liferay.analytics.reports.info.item.AnalyticsReportsInfoItem;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

/**
 * @author Cristina González
 */
@RunWith(Arquillian.class)
public class JournalArticleAnalyticsReportsInfoItemTest {

	@ClassRule
	@Rule
	public static final TestRule testRule = new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testGetAuthorName() throws Exception {
		User user = TestPropsValues.getUser();

		JournalArticle journalArticle = JournalTestUtil.addArticle(
			user.getUserId(), _group.getGroupId(), 0);

		Assert.assertEquals(
			user.getFullName(),
			_analyticsReportsInfoItem.getAuthorName(journalArticle));
	}

	@Test
	public void testGetAuthorNameWithDeletedUser() throws Exception {
		User user = UserTestUtil.addUser(_group.getGroupId());

		JournalArticle journalArticle = JournalTestUtil.addArticle(
			user.getUserId(), _group.getGroupId(), 0);

		_userLocalService.deleteUser(user);

		Assert.assertEquals(
			StringPool.BLANK,
			_analyticsReportsInfoItem.getAuthorName(journalArticle));
	}

	@Test
	public void testGetPublishDate() throws Exception {
		JournalArticle journalArticle = JournalTestUtil.addArticle(
			_group.getGroupId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString());

		Assert.assertEquals(
			journalArticle.getDisplayDate(),
			_analyticsReportsInfoItem.getPublishDate(journalArticle));
	}

	@Test
	public void testGetPublishDateWithDisplayPage() throws Exception {
		JournalArticle journalArticle = JournalTestUtil.addArticle(
			_group.getGroupId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		DDMStructure ddmStructure = journalArticle.getDDMStructure();

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.addLayoutPageTemplateEntry(
				_group.getCreatorUserId(), _group.getGroupId(), 0,
				_portal.getClassNameId(JournalArticle.class.getName()),
				ddmStructure.getStructureId(), RandomTestUtil.randomString(),
				LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE, 0, true,
				0, 0, 0, 0, serviceContext);

		AssetDisplayPageEntry assetDisplayPageEntry =
			_assetDisplayPageEntryLocalService.addAssetDisplayPageEntry(
				TestPropsValues.getUserId(), _group.getGroupId(),
				_portal.getClassNameId(JournalArticle.class.getName()),
				journalArticle.getResourcePrimKey(),
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId(),
				AssetDisplayPageConstants.TYPE_SPECIFIC,
				ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		Assert.assertEquals(
			assetDisplayPageEntry.getModifiedDate(),
			_analyticsReportsInfoItem.getPublishDate(journalArticle));
	}

	@Test
	public void testGetTitle() throws Exception {
		JournalArticle journalArticle = JournalTestUtil.addArticle(
			_group.getGroupId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString());

		Assert.assertEquals(
			journalArticle.getTitle(LocaleUtil.US),
			_analyticsReportsInfoItem.getTitle(journalArticle, LocaleUtil.US));
	}

	@Inject(filter = "component.name=*.JournalArticleAnalyticsReportsInfoItem")
	private AnalyticsReportsInfoItem _analyticsReportsInfoItem;

	@Inject
	private AssetDisplayPageEntryLocalService
		_assetDisplayPageEntryLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Inject
	private Portal _portal;

	@Inject
	private UserLocalService _userLocalService;

}