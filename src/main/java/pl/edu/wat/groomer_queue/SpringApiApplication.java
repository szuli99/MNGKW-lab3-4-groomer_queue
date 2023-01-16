package pl.edu.wat.groomer_queue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wat.groomer_queue.reflection.FieldInformation;
import pl.edu.wat.groomer_queue.reflection.Reflection;


@SpringBootApplication
public class SpringApiApplication {

    public static void main(String[] args) throws Exception {
        FieldInformation fieldInformation = new FieldInformation();
        Reflection.apply(fieldInformation.getFieldName(),fieldInformation.getFieldType());
        SpringApplication.run(SpringApiApplication.class, args);

    }

}
