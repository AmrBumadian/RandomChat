
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessagesTest {
	@Test
	void testMessageToServer() {

	}
	@Test
	void testingOptionOrNull() {
		String none = "null";
		String value = Optional.ofNullable(none).orElse("");
		assertEquals(value, "null");
	}
}
