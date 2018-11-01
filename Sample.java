package demo;

import java.util.logging.Level;
import java.util.logging.Logger;

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

	@RequestMapping("/")
    @ResponseBody
    String home() {
		
		String string = "Hello world";
		System.out.println(string);
		
		Logger log = Logger.getLogger(Application.class.getName());
        log.log(Level.INFO, "Succes: "+string);
        return string;
    }

    public static void main(String[] args) throws Exception {
    	
        SpringApplication.run(Application.class, args);
    }
}
