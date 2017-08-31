package org.springframework.cloud.sleuth.benchmarks.app;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties={"logging.level.org.springframework.cloud.sleuth=TRACE"}, webEnvironment=WebEnvironment.RANDOM_PORT)
public class SleuthBenchmarkingSpringAppTests {
	
	@Autowired
	private SleuthBenchmarkingSpringApp app;

	@Autowired
	private AppService service;

	@Autowired
	private TestRestTemplate rest;

	@Test
	public void service() throws Exception {
		assertThat(service.async().get()).isEqualTo("async");
	}

	@Test
	public void direct() throws Exception {
		assertThat(app.asyncHttp()).isEqualTo("async");
	}

	@Test
	public void http() throws Exception {
		String result = rest.getForObject("/async", String.class);
		assertThat(result).isEqualTo("async");
	}

}
