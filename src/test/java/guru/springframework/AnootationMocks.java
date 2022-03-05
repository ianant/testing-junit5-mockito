package guru.springframework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

public class AnootationMocks {

    @Mock
    Map<String,Object> mockMap;

    @BeforeEach
    void beforeEachMethod(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testMockAnnotation(){
        mockMap.put("Anant","Tripathi");
       // Assertions.assertEquals("Tripathi",mockMap.get("Anant"));
    }


}
