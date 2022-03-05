import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.mockito.Mockito.mock;

public class InLineTest {
    @Test
    void inlineMockitoTest() {
        Map mapMock=mock(Map.class);
        Assertions.assertEquals(mapMock.size(),0);
    }
}
