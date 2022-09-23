package dev.cato447.semesteressen.service;

import dev.cato447.semesteressen.domain.Participant;
import dev.cato447.semesteressen.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public void addParticipant(Participant participant){
        participantRepository.save(participant);
    }

    public void removeParticipant(Participant participant){
        participantRepository.delete(participant);
    }

}
