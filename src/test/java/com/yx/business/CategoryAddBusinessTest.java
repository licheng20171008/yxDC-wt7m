package com.yx.business;

import com.yx.dao.CategoryAdd;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CategoryAddBusinessTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void test01() {
		CategoryAddBusiness cab = new CategoryAddBusiness();
		CategoryAdd ca = new CategoryAdd();
		ca.setBusinessKey("");
		Assert.assertEquals(cab.excute(ca), "");
	}
}
