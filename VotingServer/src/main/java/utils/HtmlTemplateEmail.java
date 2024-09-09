package utils;
import java.util.Random;

public class HtmlTemplateEmail {
	


	  public static String AjouterClient(String message, String nomuser) {
	    String modelHtml = "<div class='mailbox-read-message'>";
	    modelHtml += "<p>Bonjour</p>";
	    modelHtml += "<p>vous avez un nouvaux  email  de la part de cette personne  :" + nomuser + " </p>";
	    modelHtml += "<p> "
	        + message + "  </p>";
	    modelHtml += "</div>";
	     

	    return modelHtml;
	  }

	  public static String changeStautsClient(String vote, String status) {
		    String modelHtml = "<div class='mailbox-read-message'>";
		    modelHtml += "<p>Bonjour</p>";
		    modelHtml += "<p> votre vote   :" + vote + " a eté enregister avec succeé </p>";
		   
		    modelHtml += "</div>";

		    return modelHtml;
		  }

	  public static String generatePassword(int length) {
		    String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		    StringBuilder password = new StringBuilder(length);
		    Random random = new Random(System.nanoTime());

		    for (int i = 0; i < length; i++) {
		      int index = random.nextInt(alphabet.length());
		      password.append(alphabet.charAt(index));
		    }

		    return password.toString();
		  
	  }
}
