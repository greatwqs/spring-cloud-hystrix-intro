package io.spring2go.hystrixlab.schoolservice.clients;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * ClientCallMain
 *
 * @author wangqingsong
 * Create on 2019/3/22
 */
public class ClientCallMain1 {

	// http://localhost:8088/getSchoolDetails1/abcschool
	public static void main(String[] fds) {
		for (int index = 1; index <= 8; index++) {
			Thread thread = new Thread(() -> {
				RestTemplate restTemplate = new RestTemplate();
				while (true) {
					String response = restTemplate.exchange("http://localhost:8088/getSchoolDetails1/{schoolName}",
						HttpMethod.GET,
						null, new ParameterizedTypeReference<String>() {
						}, "abcschool").getBody();
					System.out.println(response);
				}
			});
			thread.start();
		}
	}
}
