import businesslayer.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputValidationTest {

    private InputValidator inputValidator;
    @BeforeEach
    public void setUp() {
        inputValidator = new InputValidator();
    }

    @Test
    public void testIfUserInputAddressCanBeEmpty() {
        String start = "";
        String end = "";
        assertFalse(inputValidator.containsLettersThatAreOptionallyFollowedByNumbers(start, end));
    }

    @Test
    public void testIfValidationContainsOnlyNumbersOrIsEmptyCanBeEmpty() {
        String number = "";
        assertTrue(inputValidator.containsOnlyNumbersOrIsEmpty(number));
    }

    @Test
    public void testIfAddressCanContainMutatedVowels() {

    }

}
