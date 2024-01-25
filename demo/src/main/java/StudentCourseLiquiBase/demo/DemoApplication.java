package StudentCourseLiquiBase.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
		info = @Info(
				title = "Student Course Management System",
				version = "1.0.0",
				description = "This project is used to maintain Student and Course Management",
				termsOfService = "Copyright@2023",
				contact = @Contact(
						name = "Manjiri Chandure",
						email = "chanduremanjiri@gmail.com"
				),
				license = @License(
						name = "license",
						url = "students-Courses/students"
				)
		)
)
@SpringBootApplication
//@ComponentScan("StudentCourseLiquiBase.demo.MapStruct")
 public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
