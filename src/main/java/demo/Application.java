package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
public class Application {

	@RequestMapping("/devops")
    @ResponseBody
    String home() {
		
		String string = "Opticca Consulting CI/CD Demo!";
		System.out.println(string);
        return string;
    }

    public static void main(String[] args) throws Exception {
    	
        SpringApplication.run(Application.class, args);
    }
}
