package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {

    Map<Evento, List<ReceptorEventos>> receptores; // Cada evento va a tener una lista de subscriptores

    public GestorEventos(Evento... eventos) {
        receptores = new EnumMap<>(Evento.class);
        for (Evento evento : eventos) {
            receptores.put(evento, new ArrayList<>());
        }
    }

    public void suscribir(ReceptorEventos receptorEventos, Evento... eventos) {
        Objects.requireNonNull(receptorEventos, "El receptor de eventos no puede ser nulo.");
        for (Evento evento : eventos) {
            List<ReceptorEventos> suscriptores = receptores.get(evento);
            suscriptores.add(receptorEventos);
            if (receptores.containsKey(evento)) {
                receptores.replace(evento, suscriptores); // Cambia la lista relacionada con esa clave
            }
        }
    }

    public void desuscribir(ReceptorEventos receptorEventos, Evento... eventos) {
        Objects.requireNonNull(receptorEventos, "El receptor de eventos no puede ser nulo.");
        for (Evento evento : eventos) {
            List<ReceptorEventos> suscriptores = receptores.get(evento);
            suscriptores.remove(receptorEventos);
            receptores.replace(evento, suscriptores);
        }
    }

    public void notificar(Evento evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        for (ReceptorEventos receptorEventos : receptores.get(evento)) {
            receptorEventos.actualizar(evento);
        }
    }
}
