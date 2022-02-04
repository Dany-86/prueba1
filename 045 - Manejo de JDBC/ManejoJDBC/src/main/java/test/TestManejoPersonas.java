package test;

import datos.PersonaDAO;
import domain.Persona;
import java.util.*;

public class TestManejoPersonas {
    public static void main(String[] args) {
        PersonaDAO personaDao = new PersonaDAO();
        List<Persona> personas = personaDao.seleccionar();
        
        // insertando un nuevo objeto de tipo persona
//        Persona personaNueva = new Persona("Bart", "Simpson", "barto@mail.com", "5544");
//        personaDao.insertar(personaNueva);

        // Modificar un objeto de persona existente
//        Persona personaModificar = new Persona(5, "Lisa", "Simpson", "lisa@mail.com", "1122");
//        personaDao.actualizar(personaModificar);

        // Eliminar un registro(Obejto persona)
//        Persona personaEliminar = new Persona(4);
//        personaDao.eliminar(personaEliminar);
        
        

        // para iterar se puede usar tanto foreach como funcion lambda(flecha):
        personas.forEach(persona -> {
            System.out.println("persona = " + persona);
        });
    }
}
