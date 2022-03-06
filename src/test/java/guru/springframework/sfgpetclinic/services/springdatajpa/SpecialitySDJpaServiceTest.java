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
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock(lenient = true)
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
    @Test
    void testDoThrow(){
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());
        assertThrows(RuntimeException.class,()->specialtyRepository.delete(new Speciality()));
        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void findByIdThrows(){
        given(specialtyRepository.findById(1l)).willThrow(new RuntimeException("Boom"));
        assertThrows(RuntimeException.class,()->service.findById(1l));
        then(specialtyRepository).should().findById(1l);
    }

    @Test
    void testDeleteBDD(){
        willThrow(new RuntimeException()).given(specialtyRepository).delete(any());
        //this is mostly usefull when a method has void return type::
        assertThrows(RuntimeException.class,()->specialtyRepository.delete(new Speciality()));
        then(specialtyRepository).should().delete(any());
    }
    @Test
    void testSaveLambdaArgumentMatcher(){
        //given
        final String MATCH_ME="MATCH_ME";
        Speciality speciality=new Speciality();
        speciality.setDescription(MATCH_ME);
        Speciality savedSpeciality=new Speciality();
        savedSpeciality.setId(1l);

        //need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpeciality);

        //when--
        Speciality returnedSpeciality=service.save(speciality);

        //then--
        assertThat(returnedSpeciality.getId()).isEqualTo(1l);
    }

    @Test
    void testSaveLambdaArgumentMatcherNotMatch(){
        //given
        final String MATCH_ME="MATCH_ME_NOT";
        Speciality speciality=new Speciality();
        speciality.setDescription("Not_a_match");
        Speciality savedSpeciality=new Speciality();
        savedSpeciality.setId(1l);

        //need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpeciality);

        //when--
        Speciality returnedSpeciality=service.save(speciality);

        //then--
       assertNull(returnedSpeciality);
    }

}