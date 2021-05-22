package businesslayer;

import java.util.Random;

public class NameGenerator {
    public static String GenerateName(int nameLength) {
        String[] consonants = { "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "sh", "v", "w", "x", "z" };
        String[] vowels = { "a", "e", "i", "o", "u", "ae", "y" };
        String name = "";

        name += getRandom(consonants);
        name += getRandom(vowels);

        int currentLength = 2;

        while (currentLength < nameLength) {
            name += getRandom(consonants);
            name += getRandom(vowels);
            currentLength += 2;
        }

        return name;
    }

    private static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
