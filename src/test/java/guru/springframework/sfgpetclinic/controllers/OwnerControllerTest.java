package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    BindingResult result;

    @InjectMocks
    OwnerController controller;

    @Captor
    ArgumentCaptor<String> captorAnnotation;

    //this method shows the way to implement ArgumentCaptor without annotation
    @Test
    void processFindFormTestWithArgumentCaptorLineNumber39(){
        //given
        Owner owner=new Owner(1l,"anant","tripathi");
        List<Owner> ownerList=new ArrayList<>();
        final ArgumentCaptor<String> captor=ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);
        //when
        String returnValue=controller.processFindForm(owner,result,null);
        //then
        assertThat(returnValue).isEqualTo("owners/findOwners");
        assertThat("%tripathi%").isEqualTo(captor.getValue());
    }

    //this method shows the way to implement ArgumentCaptor withannotation
    @Test
    void processFindFormTestWithArgumentCaptorUsingAnnotationTestingLineNumber39(){
        //given
        Owner owner=new Owner(1l,"anant","tripathi");
        List<Owner> ownerList=new ArrayList<>();
        given(ownerService.findAllByLastNameLike(captorAnnotation.capture())).willReturn(ownerList);
        //when
        String returnValue=controller.processFindForm(owner,result,null);
        //then
        assertThat(returnValue).isEqualTo("owners/findOwners");
        assertThat("%tripathi%").isEqualTo(captorAnnotation.getValue());
    }

    @Test
    void processCreationFormIfBindingResultHasErrorTest() {
        //Given--
        Owner owner=new Owner(1l,"anant","tripathi");
        final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
        given(result.hasErrors()).willReturn(true);

        //when--
        String returnResult=controller.processCreationForm(owner,result);
        //then
        assertThat(returnResult).isEqualTo(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);
        then(ownerService.save(any(Owner.class)));
    }

    @Test
    void processCreationFormIfBindingResultHasNoErrorTest() {
        //Given--
        Owner owner=new Owner(5l,"Anant","Tripathi");
        given(result.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);
        //when--
        String savedOwner=controller.processCreationForm(owner,result);
        //then
        assertThat(savedOwner).isEqualTo("redirect:/owners/5");
        then(ownerService).should().save(any());
    }
}