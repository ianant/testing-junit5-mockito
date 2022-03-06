package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;


    @Test
    void findByIdBDDTest() {
        //Given::::
        Speciality speciality=new Speciality();
        given(specialtyRepository.findById(1l)).willReturn(Optional.of(speciality));
        //When:::
        Speciality foundSpeciality=service.findById(1l);
        //Then:::
        assertThat(foundSpeciality).isNotNull();
        //verify(specialtyRepository).findById(anyLong());
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void delete() {
        //given-- NONE
        //When--
        specialtyRepository.delete(new Speciality());
        //then--
        //verify(specialtyRepository).delete(any(Speciality.class));
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void deleteById() {
        //given--
        //when--
        specialtyRepository.deleteById(1l);
        //then--
        //verify(specialtyRepository).deleteById(1l);
        then(specialtyRepository).should().deleteById(1l);
    }
    @Test
    void deleteByIdRun2Times() {
        //given--None
        //When--
        specialtyRepository.deleteById(1l);
        specialtyRepository.deleteById(1l);
        //Then--
        //verify(specialtyRepository, times(2)).deleteById(1l);
        then(specialtyRepository).should(times(2)).deleteById(1l);
    }
    @Test
    void deleteByIdRunAtleast() {
        //Given--None
        //When--
        specialtyRepository.deleteById(1l);
        specialtyRepository.deleteById(1l);
        //Then--
        //verify(specialtyRepository, atLeast(2)).deleteById(1l);
        then(specialtyRepository).should(atLeast(2)).deleteById(1l);
    }
    @Test
    void deleteByIdRunAtMost() {
        //Given--
        //When--
        specialtyRepository.deleteById(1l);
        specialtyRepository.deleteById(1l);
        //Then--
       // verify(specialtyRepository, atMost(2)).deleteById(1l);
        then(specialtyRepository).should(atMost(2)).deleteById(1l);
    }


}