package com.chinesedreamer.eir.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml","classpath:spring-servlet.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests{

}
