package com.cefothe.bgjudge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = H2)
@ActiveProfiles("unittest")
@SpringBootTest
public class MvcApplicationTests {

	@Test
	public void contextLoads() {
	}

}
