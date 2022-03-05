package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    void findAll() {
        Visit visit=new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundVisits=service.findAll();
        assertThat(foundVisits.size()).isEqualTo(1);
        verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        Visit visit=new Visit();
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit returnedVisit=service.findById(1l);
        assertThat(returnedVisit).isNotNull();
        verify(visitRepository).findById(anyLong());
    }

    @Test
    void save() {
        Visit visit=new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit returnedVisit=service.save(new Visit());
        assertThat(returnedVisit).isNotNull();
        verify(visitRepository).save(any(Visit.class));
    }

    @Test
    void delete() {
        Visit object=new Visit();
        service.delete(object);
        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1l);
        //service.deleteById(1l);
        verify(visitRepository).deleteById(anyLong());
    }
}