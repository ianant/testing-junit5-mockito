package guru.springframework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MockitoExtentionTest {

    @Mock
    Map<String,Object> mockMap;

    @Test
    void mockingWithExtention() {
        mockMap.put("Anant","Tripathi");
    }
}
