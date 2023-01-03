import java.util.regex.*;
import java.util.Scanner;

public class AddressTokenizer {

  // Regular expressions for matching each address component
  private static final String APT_NUM_REGEX     = "(No|NO\\.)[\\s,]*(\\d+)";
  private static final String POSTCODE_REGEX    = "(\\d{5})";
  private static final String STREET_REGEX      = "(Jalan|Jln|Lorong|Persiaran|Taman|Tmn|Lrg)[\\s,]*(\\w+)*[\\s,](\\d+)(\\/)(\\d+)";
  private static final String CITY_REGEX        = "(Kuala Terengganu|Kuala Lumpur|Klang|Kajang|Bangi|Damansara|Petaling Jaya|Puchong|Subang Jaya|Cyberjaya|Putrajaya|Mantin|Kuching|Seremban)";
  private static final String STATE_REGEX       = "(Selangor|Terengganu|Pahang|Kelantan|Melaka|Pulau Pinang|Kedah|Johor|Perlis|Sabah|Sarawak)";

  public static void main(String[] args) {

    // Create scanner for user input
    Scanner scanner = new Scanner(System.in);
    try {
      System.out.println("Enter an address: ");
      String address = scanner.nextLine();

      // Regular expressions to extract the different parts of the address
      String aptNum     = getMatch(address, APT_NUM_REGEX);
      String postcode   = getMatch(address, POSTCODE_REGEX);
      String street     = getMatch(address, STREET_REGEX);
      String city       = getMatch(address, CITY_REGEX);
      String state      = getMatch(address, STATE_REGEX);
      String section    = getMatch(address, "(?i)((?!" + APT_NUM_REGEX + "|" + POSTCODE_REGEX + "|" + STREET_REGEX + "|" + CITY_REGEX + "|" + STATE_REGEX + ").)*");

      // Print out the extracted parts of the address
      System.out.println("Apartment: " + aptNum);
      System.out.println("Postcode: " + postcode);
      System.out.println("Street: " + street);
      System.out.println("City: " + city);
      System.out.println("State: " + state);
      System.out.println("Section: " + section);
    } finally {
      scanner.close();
    }
  }

  // Compile string
  private static String getMatch(String str, String regex) {
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(str);
    StringBuilder result = new StringBuilder();

      while (matcher.find()) {
        result.append(matcher.group()).append(" ");
      }

    return result.toString().trim();
  }

}