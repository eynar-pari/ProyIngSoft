package upb.webapp;

import upb.entity.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Database {
	// Create an EntityManagerFactory when you start the application.
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Upb");

	public  void main(String[] args) {

		// Create two Students
		create(1, "Libro1", "test"); // Alice will get an id 1
		create(2, "Libro2", "test1"); // Bob will get an id 2
		create(3, "Libro3", "test3"); // Charlie will get an id 3

		// Update the age of Bob using the id
		update(2, "Bob", "abc");

		// Delete the Alice from database
		delete(1);

		// Print all the Students
		List<Libro> libros = readAll();
		if (libros != null) {
			for (Libro stu : libros) {
				System.out.println(stu);
			}
		}

		// NEVER FORGET TO CLOSE THE ENTITY_MANAGER_FACTORY
		ENTITY_MANAGER_FACTORY.close();
	}

    public Database(){}

    public void closeDataBase(){ENTITY_MANAGER_FACTORY.close();}
	/**
	 * Create a new Libro.
	 * @param name
	 * @param autor
	 */
	public void create(int id, String name, String autor) {
		// Create an EntityManager
        System.out.println("Creando Libro : " + name+ " id : "+id);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// empieza transaccon
			transaction = manager.getTransaction();
			transaction.begin();
			// crea objeto
			Libro stu = new Libro();
			stu.setId(id);
			stu.setName(name);
			stu.setAutor(autor);
			// guarda libro persistentemente
			manager.persist(stu);
			// envia transaccion
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
	}

	/**
	 * leer Libros.
	 * 
	 * @return a List of Libros
	 */
	public List<Libro> readAll() {

		List<Libro> libros = null;

		// Create an EntityManager
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			transaction.begin();
			// Get Libros
			libros = manager.createQuery("SELECT s FROM Libro s", Libro.class).getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return libros;
	}

	/**
	 *Eliminar Libro
	 * @param id
	 */
	public void delete(int id) {
		// Create an EntityManager
        System.out.println("eliminar Libro: "+id);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
            Libro stu = manager.find(Libro.class, id);
            manager.remove(stu);
            transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
	}

	/**
	 * actualizando  Libro.
	 * 
	 * @param id
	 * @param name
	 * @param age
	 */
	public void update(int id, String name, String age) {
		// Create an EntityManager
        System.out.println("Actualizando Libro : "+name+ " con id : "+id);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
            Libro stu = manager.find(Libro.class, id);
			stu.setName(name);
			stu.setAutor(age);
            manager.persist(stu);

			// envia transaccion
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
	}
}
