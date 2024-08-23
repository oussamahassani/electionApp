package utils;

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

	
}
