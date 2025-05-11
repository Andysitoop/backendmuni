package com.municipalidad.service;

import com.municipalidad.model.Ticket;
import com.municipalidad.model.Usuario;
import com.municipalidad.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    public Ticket crearTicket(Ticket.TipoServicio tipoServicio, Usuario usuario) {
        Ticket ticket = new Ticket();
        ticket.setTipoServicio(tipoServicio);
        ticket.setUsuario(usuario);
        ticket.setFechaCreacion(LocalDateTime.now());
        ticket.setEstado(Ticket.EstadoTicket.PENDIENTE);
        ticket.setNumeroTicket(generarNumeroTicket(tipoServicio));
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket atenderTicket(Long ticketId, Usuario atendidoPor) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            ticket.setEstado(Ticket.EstadoTicket.EN_ATENCION);
            ticket.setAtendidoPor(atendidoPor);
            ticket.setFechaAtencion(LocalDateTime.now());
            return ticketRepository.save(ticket);
        }
        return null;
    }

    @Transactional
    public Ticket finalizarTicket(Long ticketId) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            ticket.setEstado(Ticket.EstadoTicket.ATENDIDO);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    public List<Ticket> obtenerTicketsPendientes() {
        return ticketRepository.findByEstadoOrderByFechaCreacionAsc(Ticket.EstadoTicket.PENDIENTE);
    }

    public List<Ticket> obtenerTicketsPorTipo(Ticket.TipoServicio tipoServicio) {
        return ticketRepository.findByTipoServicioAndEstadoOrderByFechaCreacionAsc(tipoServicio, Ticket.EstadoTicket.PENDIENTE);
    }

    private String generarNumeroTicket(Ticket.TipoServicio tipoServicio) {
        String prefix = tipoServicio.name().substring(0, 3);
        return prefix + "-" + System.currentTimeMillis();
    }
} 