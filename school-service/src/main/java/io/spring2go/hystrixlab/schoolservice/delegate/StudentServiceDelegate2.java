package io.spring2go.hystrixlab.schoolservice.delegate;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class StudentServiceDelegate2 {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Configuration details
	 * https://github.com/Netflix/Hystrix/wiki/Configuration
	 */
	@HystrixCommand(fallbackMethod = "callStudentService_Fallback2",
		commandProperties = {
		    // execution
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			// circuitBreaker
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
			// fallback
			@HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10")
		},
		threadPoolKey = "studentServiceThreadPool2",
		threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "5"),
			@HystrixProperty(name = "maxQueueSize", value = "5")
		})
	public String callStudentService2(String schoolName) {
		String response = restTemplate.exchange("http://localhost:8090/getStudentBySchool/{schoolName}", HttpMethod.GET,
			null, new ParameterizedTypeReference<String>() {
			}, schoolName).getBody();
		return "NORMAL FLOW !!! - School Name - " + schoolName + " ::: " + " Student Details " + response + " - "
			+ new Date();
	}

	@SuppressWarnings("unused")
	private String callStudentService_Fallback2(String schoolName) {
		return "Fallback !!! - " + new Date();
	}
}
