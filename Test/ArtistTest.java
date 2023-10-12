import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class ArtistTest {

    @Test
    void testIdValidation() {
        // Valid ID - accepted into database
        Artist artist1 = new Artist("569MMMRR_%", "Name", "City|State|Country", "01-01-2000", "This is a sample bio with more than ten words to meet the conditions specified.", null, null, null);
        assertFalse(artist1.addArtist());

        // Invalid ID - wrong starting number
        Artist artist2 = new Artist("459MMMRR_%", "Name", "City|State|Country", "01-01-2000", "This is a sample bio with more than ten words to meet the conditions specified.", null, null, null);
        assertFalse(artist2.addArtist());

        // Invalid ID - lowercase letter
        Artist artist3 = new Artist("569MmMRR_%", "Name", "City|State|Country", "01-01-2000", "This is a sample bio with more than ten words to meet the conditions specified.", null, null, null);
        assertFalse(artist3.addArtist());
    }

    @Test
    void testBirthdateValidation() {
        // Valid Birthdate - accepted into database
        Artist artist1 = new Artist("569MMMRR_%", "Name", "City|State|Country", "01-01-1990", "This is a sample bio with more than ten words to meet the conditions specified.", null, null, null);
        assertTrue(artist1.isValidBirthdate());

        // Invalid Birthdate - wrong month
        Artist artist3 = new Artist("569MMMRR_%", "Name", "City|State|Country", "05-13-1980", "This is a sample bio with more than ten words to meet the conditions specified.", null, null, null);
        assertFalse(artist3.isValidBirthdate());
    }

    @Test
    void testAddressValidation() {
        // Valid Address - accepted into database
        Artist artist1 = new Artist("569MMMRR_%", "Name", "Melbourne|Victoria|Australia", "05-11-1980", "This is a bio with exactly ten words now.", null, null, null);
        assertTrue(artist1.isValidAddress());

        // Invalid Address - not adding all 3 sections
        Artist artist2 = new Artist("569MMMRR_%", "Name", "Melbourne|Victoria", "05-11-1980", "This is a bio with exactly ten words now.", null, null, null);
        assertFalse(artist2.isValidAddress());
    }

    @Test
    void testBioValidation() {
        // Valid Bio - accepted into database
        Artist artist1 = new Artist("569MMMRR_%", "Name", "Melbourne|Victoria|Australia", "05-11-1980", "This is a sample bio with more than ten words to meet the conditions specified.", null, null, null);
        assertTrue(artist1.isValidBio());

        // Invalid Bio - Short Bio length
        Artist artist2 = new Artist("569MMMRR_%", "Name", "Melbourne|Victoria|Australia", "05-11-1980", "Short bio.", null, null, null);
        assertFalse(artist2.isValidBio());

        // Invalid Bio - Long Bio length
        Artist artist3 = new Artist("569MMMRR_%", "Name", "Melbourne|Victoria|Australia", "05-11-1980", "This bio is way too long for the given requirements as it clearly exceeds the thirty-word limit that was set. Which makes it fail and not get added to the database as it is denied through the conditions set.", null, null, null);
        assertFalse(artist3.isValidBio());
    }

    @Test
    void testOccupationValidation() {
        // Valid Occupation - accepted into database
        ArrayList<String> occupations1 = new ArrayList<>();
        occupations1.add("Singer");
        Artist artist1 = new Artist("569MMMRR_%", "Name", "Melbourne|Victoria|Australia", "05-11-1980", "Bio with ten words exactly here now.", occupations1, null, null);
        assertTrue(artist1.addArtist());

        // Invalid Occupation - No occupation
        ArrayList<String> occupations2 = new ArrayList<>();
        Artist artist2 = new Artist("569MMMRR_%", "Name", "Melbourne|Victoria|Australia", "05-11-1980", "Bio with ten words exactly here now.", occupations2, null, null);
        assertFalse(artist2.addArtist());

        // Invalid Occupation - 5 occupations
        ArrayList<String> occupations3 = new ArrayList<>();
        occupations3.add("Singer"); occupations3.add("Songwriter"); occupations3.add("Dancer");
        occupations3.add("Composer"); occupations3.add("Producer"); occupations3.add("Actor");
        Artist artist3 = new Artist("569MMMRR_%", "Name", "Melbourne|Victoria|Australia", "05-11-1980", "Bio with ten words exactly here now.", occupations3, null, null);
        assertFalse(artist3.addArtist());
    }

    @Test
    void testAwardsValidation() {
        // Correct data
        ArrayList<String> validAwards = new ArrayList<>();
        validAwards.add("2022, Best Song For Movie");
        validAwards.add("2021, Outstanding Original Score");

        // Wrong data
        ArrayList<String> invalidAwards = new ArrayList<>();
        invalidAwards.add("202, Short Title");
        invalidAwards.add("2022, This is an overly long title that clearly exceeds the ten-word limit");

        // Valid Award - accepted into the database
        Artist artist1 = new Artist("569MMMRR_%", "Name", "Melbourne|Victoria|Australia", "05-11-1980", "Bio with exactly more than ten words in total for testing.", null, null, validAwards);
        assertTrue(artist1.addArtist());

        // Invalid Award - wrong data used
        Artist artist2 = new Artist("569MMMRR_%", "Name", "Melbourne|Victoria|Australia", "05-11-1980", "Bio with exactly more than ten words in total for testing.", null, null, invalidAwards);
        assertFalse(artist2.addArtist());
    }

}