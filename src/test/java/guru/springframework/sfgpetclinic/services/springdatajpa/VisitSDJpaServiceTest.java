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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;
/*
    @Test
    void findAll() {
        //Given--
        Visit visit=new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundVisits=service.findAll();
        assertThat(foundVisits.size()).isEqualTo(1);
        verify(visitRepository).findAll();
    }*/
    @Test
    void findAll() {
        //Given
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        given(visitRepository.findAll()).willReturn(visits);
        //when
        Set<Visit> foundVisits = service.findAll();
        //then--
        then(visitRepository).should().findAll();
        assertThat(foundVisits.size()).isEqualTo(1);
    }
    /*@Test
    void findById() {
        //Given--
        Visit visit=new Visit();
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit returnedVisit=service.findById(1l);
        assertThat(returnedVisit).isNotNull();
        verify(visitRepository).findById(anyLong());
    }*/
    @Test
    void findById() {
        //Given--
        Visit visit=new Visit();
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));
        //When--
        Visit returnedVisit=service.findById(1l);
        //Then--
        assertThat(returnedVisit).isNotNull();
        then(visitRepository).should().findById(anyLong());
    }


   /* @Test
    void save() {
        //Given
        Visit visit=new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit returnedVisit=service.save(new Visit());
        assertThat(returnedVisit).isNotNull();
        verify(visitRepository).save(any(Visit.class));
    }*/

    @Test
    void save() {
        //Given
        Visit visit=new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(visit);
        //When--
        Visit returnedVisit=service.save(new Visit());
        //Then--
        assertThat(returnedVisit).isNotNull();
        then(visitRepository).should().save(any(Visit.class));
    }

    /*@Test
    void delete() {
        Visit object=new Visit();
        service.delete(object);
        verify(visitRepository).delete(any(Visit.class));
    }*/
    @Test
    void delete() {
        //Given--
        Visit object=new Visit();
        //When--
        service.delete(object);
        //Then--
        then(visitRepository).should().delete(any(Visit.class));
    }

   /* @Test
    void deleteById() {
        service.deleteById(1l);
        //service.deleteById(1l);
        verify(visitRepository).deleteById(anyLong());
    }*/
   @Test
   void deleteById() {
       //Given--None
       //When--
       service.deleteById(1l);
       //service.deleteById(1l);
       //Then--
       then(visitRepository).should().deleteById(anyLong());
   }
}