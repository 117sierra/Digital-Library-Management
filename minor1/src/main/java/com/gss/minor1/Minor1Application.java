package com.gss.minor1;

//import com.gss.minor1.Repository.TxnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Minor1Application implements CommandLineRunner {
//	@Autowired
//	private TxnRepository txnRepository;

	public static void main(String[] args) {

		SpringApplication.run(Minor1Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		txnRepository.updatecreateon();
	}
}
