package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		/* Lombok 적용 테스트
		LombokTest lombokTest = new LombokTest();
		lombokTest.setData("Test");
		System.out.println(lombokTest.getData());
		*/

		SpringApplication.run(JpashopApplication.class, args);
	}

}
