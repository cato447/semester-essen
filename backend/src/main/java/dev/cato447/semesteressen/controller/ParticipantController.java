package dev.cato447.semesteressen.controller;

import dev.cato447.semesteressen.domain.Event;
import dev.cato447.semesteressen.domain.Participant;
import dev.cato447.semesteressen.repository.EventRepository;
import dev.cato447.semesteressen.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ParticipantController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @GetMapping("/participants")
    public ResponseEntity<List<Participant>> getAllParticipants() {

        List<Participant> participants = new ArrayList<>(participantRepository.findAll());

        if (participants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    @GetMapping("/events/{eventId}/participants")
    public ResponseEntity<List<Participant>> getAllParticipantsByEventId(@PathVariable(value = "eventId") Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Not found Event with id = " + eventId);
        }

        List<Participant> participants = participantRepository.findParticipantsByEventsId(eventId);
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    @GetMapping("/participants/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable(value = "id") Long id) {
        Participant tag = participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Participant with id = " + id));

        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @GetMapping("/participants/{participantId}/events")
    public ResponseEntity<List<Event>> getAllEventsByParticipantId(@PathVariable(value = "participantId") Long participantId) {
        if (!participantRepository.existsById(participantId)) {
            throw new RuntimeException("Not found Participant with id = " + participantId);
        }

        List<Event> events = eventRepository.findEventsByParticipantsId(participantId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/events/{eventId}/participants")
    public ResponseEntity<Participant> addParticipant(@PathVariable(value = "eventId") Long eventId, @RequestBody Participant participantRequest) {
        Participant participant = eventRepository.findById(eventId).map(found_event -> {
            Long participantId = participantRequest.getId();

            // participant exists
            if (participantId != null) {
                Participant existing_participant = participantRepository.findById(participantId)
                        .orElseThrow(() -> new RuntimeException("Not found Participant with id = " + participantId));
                found_event.addParticipant(existing_participant);
                eventRepository.save(found_event);
                return existing_participant;
            }

            // add and create new Tag
            found_event.addParticipant(participantRequest);
            return participantRepository.save(participantRequest);
        }).orElseThrow(() -> new RuntimeException("Not found Event with id = " + eventId));

        return new ResponseEntity<>(participant, HttpStatus.CREATED);
    }

    @PutMapping("/participants/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable("id") long id, @RequestBody Participant participantRequest) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ParticipantId " + id + " not found"));

        participant.update(participantRequest);

        return new ResponseEntity<>(participantRepository.save(participant), HttpStatus.OK);
    }

    @DeleteMapping("/events/{eventId}/participants/{participantId}")
    public ResponseEntity<HttpStatus> deleteParticipantFromEvent(@PathVariable(value = "eventId") Long eventId, @PathVariable(value = "participantId") Long participantId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Not found Event with id = " + eventId));

        event.removeParticipant(participantRepository.findById(participantId).orElseThrow(() -> new RuntimeException("ParticipantId " + participantId + " not found")));
        eventRepository.save(event);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/participants/{participant}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
        participantRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
