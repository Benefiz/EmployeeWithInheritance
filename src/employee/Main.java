package employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ekkasit.tan
 */
public class Main {

    public static void main(String[] args) {
        FullTimeEmployee emp1 = new FullTimeEmployee();
        PartTimeEmployee emp2 = new PartTimeEmployee();
        // Set Properties
        emp1.setName("Jane");
        emp1.setSalary(5000);
        emp2.setName("Robert");
        emp2.setHoursWork(3);
        // Insert employees
        insertEmployee(emp1);
        insertEmployee(emp2);
        // Update employees
        emp1.setSalary(6000);
        updateFullTimeEmployee(emp1);
        emp2.setHoursWork(4);
        updatePartTimeEmployee(emp2);
        // Remove employees
        removeFullTimeEmployee(emp1);
        removePartTimeEmployee(emp2);
    }

    public static void insertEmployee(Object emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(emp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public static void updateFullTimeEmployee(FullTimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
        EntityManager em = emf.createEntityManager();
        FullTimeEmployee fromDb = em.find(FullTimeEmployee.class, emp.getId());
        fromDb.setSalary(emp.getSalary());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void updatePartTimeEmployee(PartTimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
        EntityManager em = emf.createEntityManager();
        PartTimeEmployee fromDb = em.find(PartTimeEmployee.class, emp.getId());
        fromDb.setHoursWork(emp.getHoursWork());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void removeFullTimeEmployee(FullTimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
        EntityManager em = emf.createEntityManager();
        FullTimeEmployee fromDb = em.find(FullTimeEmployee.class, emp.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void removePartTimeEmployee(PartTimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
        EntityManager em = emf.createEntityManager();
        PartTimeEmployee fromDb = em.find(PartTimeEmployee.class, emp.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
