package org.academy.hibernate;

import org.academy.hibernate.entities.Adresse;
import org.academy.hibernate.entities.Personne;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

@SuppressWarnings( { "unused", "unchecked" })
public class Main {

	// constantes
	private final static String TABLE_NAME = "jpa02_hb_personne";

	// Contexte de persistance
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

	private static EntityManager em = null;

	// objets partag�s
	private static Personne p1, p2, newp1;

	private static Adresse a1, a2, a3, a4, newa1, newa4;

	public static void main(String[] args) throws Exception {
		// on r�cup�re un EntityManager � partir de l'EntityManagerFactory
		em = emf.createEntityManager();

		// nettoyage base
		log("clean");
		clean();

		// dump
		dumpPersonne();

		// test1
		log("test1");
		test1();

		// test2
		log("test2");
		test2();

		// test4
		log("test4");
		test4();

		// test5
		log("test5");
		test5();
		// fin contexte de persistance
		if (em != null && em.isOpen())
			em.close();

		// fermeture EntityManagerFactory
		emf.close();
	}

	// r�cup�rer l'EntityManager courant
	private static EntityManager getEntityManager() {
		if (em == null || !em.isOpen()) {
			em = emf.createEntityManager();
		}
		return em;
	}

	// r�cup�rer un EntityManager neuf
	private static EntityManager getNewEntityManager() {
		if (em != null && em.isOpen()) {
			em.close();
		}
		em = emf.createEntityManager();
		return em;
	}

	// affichage contenu table Personne
	private static void dumpPersonne() {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// affichage personnes
		System.out.println("[personnes]");
		for (Object p : em.createQuery("select p from Personne p order by p.nom asc").getResultList()) {
			System.out.println(p);
		}
		// fin transaction
		tx.commit();
	}

	// raz BD
	private static void clean() {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// requ�te
		Query sql1;
		// supprimer les �l�ments de la table PERSONNE
		sql1 = em.createNativeQuery("delete from " + TABLE_NAME);
		sql1.executeUpdate();
		// fin transaction
		tx.commit();
	}

	// logs
	private static void log(String message) {
		System.out.println("main : ----------- " + message);
	}

	// cr�ation d'objets
	public static void test1() throws ParseException {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// cr�ation personnes
		p1 = new Personne("Martin", "Paul", new SimpleDateFormat("dd/MM/yy").parse("31/01/2000"), true, 2);
		p2 = new Personne("Durant", "Sylvie", new SimpleDateFormat("dd/MM/yy").parse("05/07/2001"), false, 0);
		// cr�ation adresses
		a1 = new Adresse("8 rue Boileau", null, null, "49000", "Angers", null, "France");
		a2 = new Adresse("Apt 100", "Les Mimosas", "15 av Foch", "49002", "Angers", "03", "France");
		// associations personne <--> adresse
		p1.setAdresse(a1);
		p2.setAdresse(a2);
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// persistance des personnes
		em.persist(p1);
		em.persist(p2);
		// fin transaction
		tx.commit();
		// dump
		dumpPersonne();
	}

	// modifier un objet du contexte
	public static void test2() {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// on incr�mente le nbre d'enfants de p1
		p1.setNbenfants(p1.getNbenfants() + 1);
		// on modifie son �tat marital
		p1.setMarie(false);
		// l'objet p1 est automatiquement sauvegard� (dirty checking)
		// lors de la prochaine synchronisation (commit ou select)
		// fin transaction
		tx.commit();
		// on affiche la nouvelle table
		dumpPersonne();
	}

	// supprimer un objet appartenant au contexte de persistance
	public static void test4() {
		// contexte de persistance
		EntityManager em = getEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// on supprime l'objet attach� p2
		em.remove(p2);
		// fin transaction
		tx.commit();
		// on affiche la nouvelle table
		dumpPersonne();
	}

	// d�tacher, r�attacher et modifier
	public static void test5() {
		// nouveau contexte de persistance
		EntityManager em = getNewEntityManager();
		// d�but transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// on r�attache p1 au nouveau contexte
		p1 = em.find(Personne.class, p1.getId());
		// fin transaction
		tx.commit();
		// on change l'adresse de p1
		p1.getAdresse().setVille("Paris");
		// on affiche la nouvelle table
		dumpPersonne();
	}

}