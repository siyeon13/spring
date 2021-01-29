package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/kr/or/ddit/ioc/typeConversion.xml")
public class TypeConversionTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TypeConversionTest.class);

	
	@Resource(name = "user")
	private UserVo user; 
	
	@Test
	public void usertest() {
		
		
		logger.debug("user.getReg_dt() : {}", user.getReg_dt());
		logger.debug("user.getHire_dt() : {}", user.getHire_dt());
		logger.debug("user.getPrice_dt() : {}", user.getPrice());
		
		
		
		
	}

}
