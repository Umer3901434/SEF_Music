import java.util.ArrayList;
import java.util.regex.Pattern;

public class Artist {

    private String ID;
    private String Name;
    private String Address;
    private String Birthdate;
    private String Bio;
    private ArrayList <String> Occupations;
    private ArrayList <String> Genres;
    private ArrayList <String> Awards;

    public Artist(String id, String name, String address, String birthdate, String bio,
                  ArrayList <String> occupations, ArrayList <String> genres, ArrayList <String> awards)
    {
        ID=id;
        Name=name;
        Address=address;
        Birthdate=birthdate;
        Bio=bio;
        Occupations=occupations;
        Genres=genres;
        Awards=awards;
    }

    // Condition 1 ID
    public boolean isValidId() {
        // Allowing only 10 words to be accepted
        if (ID.length() != 10) {
            return false;
        }

        // First characters to be numbers between 5-9
        for (int i = 0; i < 3; i++) {
            char c = ID.charAt(i);
            if (c < '5' || c > '9') {
                return false;
            }
        }

        // 4th to 8th character to be capital letters
        for (int i = 3; i < 8; i++) {
            char c = ID.charAt(i);
            if (c < 'A' || c > 'Z') {
                return false;
            }
        }

        // 9th and 10 character to be special characters
        char c8 = ID.charAt(8);
        char c9 = ID.charAt(9);
        if (!(c8 < 'A' || (c8 > 'Z' && c8 < 'a') || c8 > 'z') ||
                !(c9 < 'A' || (c9 > 'Z' && c9 < 'a') || c9 > 'z')) {
            return false;
        }

        return true;
    }

    // Condition 2 BirthDate
    public boolean isValidBirthdate() {
        // How the date should look like and how many integers are allowed
        String regex = "^\\d{2}-\\d{2}-\\d{4}$";
        if (Pattern.matches(regex, Birthdate)) {
            int day = Integer.parseInt(Birthdate.substring(0, 2));
            int month = Integer.parseInt(Birthdate.substring(3, 5));

            // Limiting numbers for days and months
            if (day >= 1 && day <= 31 && month >= 1 && month <= 12) {
                return true;
            }
        }
        return false;
    }

    // Condition 3 Address
    public boolean isValidAddress() {
        // Adding what needs to be added between the city, state and country
        String[] addressParts = Address.split("\\|");
        return addressParts.length == 3; // Ensure there are three parts: City, State, Country
    }

    // Condition 4 Bio
    public boolean isValidBio() {
        String[] words = Bio.trim().split("\\s+");
        //Limiting the amount of words used for bio to 30
        return words.length >= 10 && words.length <= 30;
    }

    // Condition 5 Occupations
    public boolean isValidOccupations() {
        //Limiting how many occupations can be added to 5
        return Occupations != null && Occupations.size() >= 1 && Occupations.size() <= 5;
    }

    // Condition 6
    public boolean isValidAwards() {
        // There can be 0 awards for artist
        if (Awards == null) {
            return true;
        }

        // Maximum of 3 awards are allowed
        if (Awards.size() > 3) {
            return false;
        }

        String regex = "^\\d{4},\\s.+";

        for (String award : Awards) {
            if (!Pattern.matches(regex, award)) {
                return false;
            }
            // Title can only be 4 to 10 words
            String title = award.split(",")[1].trim();
            int wordCount = title.split("\\s+").length;
            if (wordCount < 4 || wordCount > 10) {
                return false;
            }
        }
        return true;
    }

    // AddArtist function
    public boolean addArtist()
    {
        // Adds all the information into the database for the artist
        return isValidId() && isValidBirthdate() && isValidAddress() && isValidBio() && isValidOccupations() && isValidAwards();

    }

    // UpdateArtist function
    public boolean updateArtist()
    {

        return true;
    }
}