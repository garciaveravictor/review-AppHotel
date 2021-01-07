
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
import java.util.List;

/**
 *
 * @author Rafaa
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BDHotel bd = new BDHotel();
        
        //List<ReservasSalon> list = bd.listaReservasSalon();
        List<ReservasHab> list = bd.listaReservasHab();
        
        for (ReservasHab r : list) {
            System.out.println(r.toString());
            
        }
        
        bd.cerrarConexion();

    }

}

