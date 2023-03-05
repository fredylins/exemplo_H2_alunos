package br.edu.unoesc.exemplo_H2;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;

import br.edu.unoesc.exemplo_H2.model.Aluno;
import br.edu.unoesc.exemplo_H2.repository.AlunoRepository;

@SpringBootApplication
public class ExemploH2Application {
	public static void main(String[] args) {
		SpringApplication.run(ExemploH2Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AlunoRepository repositorio) {
		return args -> {
			Aluno l = new Aluno(null, "Sicrano", 
								new BigDecimal("666"), 
								LocalDate.of(2010, 5, 20));
			repositorio.save(l);
			
			try {						
			} catch (EmptyResultDataAccessException e) {
				System.out.println("Erro! Registro não encontrado!");
			} catch (RuntimeException e) {
				System.out.println("Erro de execução!");
			}
			
			Optional<Aluno> a = repositorio.findById(1L);
			if (!a.isPresent()) {
				System.out.println("Aluno não existe!");
			} else {
				System.out.println(a);
				System.out.println(a.get());
				System.out.println(a.get().getNome());
			}
			
		};
	}
}