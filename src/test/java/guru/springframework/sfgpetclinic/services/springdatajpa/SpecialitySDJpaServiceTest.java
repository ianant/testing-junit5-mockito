package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void delete() {
        specialtyRepository.delete(new Speciality());
    }

    @Test
    void deleteById() {
        specialtyRepository.deleteById(1l);
        verify(specialtyRepository).deleteById(1l);
    }
    @Test
    void deleteByIdRun2Times() {
        specialtyRepository.deleteById(1l);
        specialtyRepository.deleteById(1l);
        verify(specialtyRepository, times(2)).deleteById(1l);
    }
    @Test
    void deleteByIdRunAtleast() {
        specialtyRepository.deleteById(1l);
        specialtyRepository.deleteById(1l);
        verify(specialtyRepository, atLeast(2)).deleteById(1l);
    }
    @Test
    void deleteByIdRunAtMost() {
        specialtyRepository.deleteById(1l);
        specialtyRepository.deleteById(1l);
        verify(specialtyRepository, atMost(2)).deleteById(1l);
    }

}