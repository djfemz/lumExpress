package africa.semicolon.lumexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;

@SpringBootApplication
public class LumExpressApplication {

	public static void main(String[] args) {

		SpringApplication.run(LumExpressApplication.class, args);

	}

}
