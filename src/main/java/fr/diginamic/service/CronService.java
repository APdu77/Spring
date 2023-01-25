package fr.diginamic.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CronService {

	// Valeur mise dans un champ
	@Value("${prenom}")
	private String prenom;

	// Valeur directement utilisee sans annotation
	//@Scheduled(cron = "${cron.test}")
	public void nightShift() {
		System.out.println("C'est l'heure " + prenom);
	}
}
