package br.com.softplan.softplandesafiofullstackmarcelokaufmann;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SoftplanDesafioFullstackMarceloKaufmannApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftplanDesafioFullstackMarceloKaufmannApplication.class, args);

		//System.out.print("-123-");
		//System.out.print(new BCryptPasswordEncoder().encode("123"));
		//System.out.print("-456-");
		//System.out.print(new BCryptPasswordEncoder().encode("456"));
		//System.out.print("-789-");
		//System.out.print(new BCryptPasswordEncoder().encode("789"));
	}

}
