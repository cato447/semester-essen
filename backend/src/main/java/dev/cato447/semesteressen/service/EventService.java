package dev.cato447.semesteressen.service;

import dev.cato447.semesteressen.domain.Event;
import dev.cato447.semesteressen.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void addEvent(Event event){
        eventRepository.save(event);
    }

    public void removeEvent(Event event){
        eventRepository.delete(event);
    }
}
