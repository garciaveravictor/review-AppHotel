
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import entidades.Cliente;
import entidades.Provincia;
import entidades.ReservasHab;
import entidades.ReservasSalon;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Rafaa
 */
public class BDHotel {

    private EntityManagerFactory emf;
    private EntityManager em;

    public static final char BANQUETE = 'B';
    public static final char JORNADA = 'J';
    public static final char CONGRESO = 'C';

    public BDHotel() {
        Map<String, String> emfProperties = new HashMap<String, String>();
        emfProperties.put("javax.persistence.schema-generation.database.action", "create");
        emfProperties.put("javax.persistence.jdbc.url", "jdbc:derby:BDHotel;create=true");

        emf = Persistence.createEntityManagerFactory("AppHotelPU", emfProperties);
        em = emf.createEntityManager();

    }

    public Cliente altaCliente(String values[]) {
        Cliente cliente = new Cliente();
        cliente.setDni(values[0]);
        cliente.setNombre(values[1]);
        cliente.setDireccion(values[2]);
        cliente.setLocalidad(values[3]);
        cliente.setTelefono("000000000");

        Query queryProvincia = em.createNamedQuery("Provincia.findById");
        queryProvincia.setParameter("id", Integer.parseInt(values[4]));

        List<Provincia> listProvincia = queryProvincia.getResultList();
        Provincia provincia = listProvincia.get(0);

        cliente.setProvincia(provincia);

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();

        return cliente;
    }

    public Cliente altaClienteTfno(String values[]) {
        Cliente cliente = new Cliente();

        cliente.setDni(values[0]);
        cliente.setNombre(values[1]);
        cliente.setDireccion(values[2]);
        cliente.setTelefono(values[3]);

        Query queryProvincia = em.createNamedQuery("Provincia.findById");
        queryProvincia.setParameter("id", Integer.parseInt("1"));

        List<Provincia> listProvincia = queryProvincia.getResultList();
        Provincia provincia = listProvincia.get(0);

        cliente.setProvincia(provincia);

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();

        return cliente;
    }

    public Cliente buscarCliente(String dni) {
        Cliente c;

        Query queryCliente = em.createNamedQuery("Cliente.findByDni");
        queryCliente.setParameter("dni", dni);
        List<Cliente> listCliente = queryCliente.getResultList();

        if (!listCliente.isEmpty()) {
            c = listCliente.get(0);
        } else {
            c = null;
        }

        return c;
    }

    public void registroReservaHab(LocalDate fecha_in, LocalDate fecha_out, Cliente cliente, String values[]) {

        ZonedDateTime zonedDateTime = fecha_in.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        Date date1 = Date.from(instant);

        zonedDateTime = fecha_out.atStartOfDay(ZoneId.systemDefault());
        instant = zonedDateTime.toInstant();
        Date date2 = Date.from(instant);

        ReservasHab reserva = new ReservasHab(date1, date2, cliente.getDni());

        reserva.setNHab(Integer.parseInt(values[0]));
        reserva.setTipo(values[1]);
        reserva.setRegimen(values[2].charAt(0));
        reserva.setFumador(values[3].charAt(0));

        em.getTransaction().begin();
        em.persist(reserva);
        em.getTransaction().commit();
    }

    public void registroReservaSalonBanquete(Cliente c, LocalDate localDate, String values[]) {

        /*
        Tomara 3 parametros -> 
            Un objeto de tipo cliente, buscado o dado de alta anteriormente
            Un objeto de tipo LocalDate, pasado directamente desde el datepicker
            Un array de string que englobara 2 valores
                values[0] numero personas
                values[1] tipo de cocina {B,V,C,P,N}
         */
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        Date date = Date.from(instant);

        ReservasSalon reserva = new ReservasSalon(date, c.getDni());

        reserva.setTipoEvento(BANQUETE);
        reserva.setNumPersonas(Integer.parseInt(values[0]));
        reserva.setTipoCocina(values[1].charAt(0));

        em.getTransaction().begin();
        em.persist(reserva);
        em.getTransaction().commit();
    }

    public void registroReservaSalonJornada(Cliente c, LocalDate localDate, String numPersonas) {

        /*
        Tomara 3 parametros -> 
            Un objeto de tipo cliente, buscado o dado de alta anteriormente
            Un objeto de tipo LocalDate, pasado directamente desde el datepicker
            Un string con el numero de personas
         */
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        Date date = Date.from(instant);

        ReservasSalon reserva = new ReservasSalon(date, c.getDni());

        reserva.setTipoEvento(JORNADA);
        reserva.setNumPersonas(Integer.parseInt(numPersonas));
        reserva.setTipoCocina('N');

        em.getTransaction().begin();
        em.persist(reserva);
        em.getTransaction().commit();
    }

    public void registroReservaSalonCongreso(Cliente c, LocalDate localDate, String values[]) {

        /*
        Tomara 3 parametros -> 
            Un objeto de tipo cliente, buscado o dado de alta anteriormente
            Un objeto de tipo LocalDate, pasado directamente desde el datepicker
            Un array de string que englobara 2 valores
                values[0] numero personas
                values[1] numero de habitaciones (0 si no necesita)
                values[2] numero de dias
         */
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        Date date = Date.from(instant);

        ReservasSalon reserva = new ReservasSalon(date, c.getDni());

        reserva.setTipoEvento(CONGRESO);
        reserva.setNumPersonas(Integer.parseInt(values[0]));
        reserva.setNumHabitaciones(Integer.parseInt(values[1]));
        reserva.setNumDias(Integer.parseInt(values[2]));
        reserva.setTipoCocina('N');

        em.getTransaction().begin();
        em.persist(reserva);
        em.getTransaction().commit();
    }

    public ReservasSalon bucarReservaSalon(String dni, LocalDate localDate) {

        ReservasSalon rs;

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        Date date = Date.from(instant);

        Query queryReservaSalon = em.createNamedQuery("ReservasSalon.findByReservasSalonPK");
        queryReservaSalon.setParameter("dni", dni);
        queryReservaSalon.setParameter("fecha", date);

        List<ReservasSalon> listReservasSalon = queryReservaSalon.getResultList();

        if (!listReservasSalon.isEmpty()) {
            rs = listReservasSalon.get(0);
        } else {
            rs = null;
        }

        return rs;
    }

    public List<ReservasSalon> listaReservasSalon() {
        Query queryReservaSalon = em.createNamedQuery("ReservasSalon.findAll");
        return queryReservaSalon.getResultList();

    }

    public List<ReservasHab> listaReservasHab() {
        Query queryReservaHab = em.createNamedQuery("ReservasHab.findAll");
        return queryReservaHab.getResultList();

    }

    public List<Cliente> listaClientes() {
        Query queryCliente = em.createNamedQuery("Cliente.findAll");
        return queryCliente.getResultList();

    }

    public List<Provincia> listaProvincias() {
        Query queryProvincias = em.createNamedQuery("Provincia.findAll");
        return queryProvincias.getResultList();

    }

    public void cerrarConexion() {
        em.close();
        emf.close();
        try {
            DriverManager.getConnection("jdbc:derby:BDAgenda;shutdown=true");
        } catch (SQLException ex) {
        }
    }

}
