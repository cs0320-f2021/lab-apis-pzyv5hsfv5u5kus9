package edu.brown.cs.student.client;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * This class generates the HttpRequests that are then used to make requests from the ApiClient.
 */
public class ClientRequestGenerator {

  /**
   * The basic introductory GET request. You should fill it out so it calls our server at the given URL.
   *
   * @return an HttpRequest object for accessing the introductory resource.
   */
  public static HttpRequest getIntroGetRequest() {
    // The resource we want is hosted at https://cq2gahtw4j.execute-api.us-east-1.amazonaws.com/.
    String reqUri = "https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/introResource";


    // TODO build and return a new GET HttpRequest.

    // See https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.html and
    // https://docs.oracle.com/en/java/javase/1/docs/api/java.net.http/java/net/http/HttpRequest.Builder.html
    return HttpRequest.newBuilder()
        .uri(URI.create(reqUri))
        .build();
  }

  /**
   * Similar to the introductory GET request, but restricted to api key holders only. Try calling it without the API
   * Key configured and see what happens!
   *
   * @return an HttpRequest object for accessing the secured resource.
   */
  public static HttpRequest getSecuredGetRequest() {
    String reqUri = "https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/securedResource";
    // TODO get the secret API key by using the ClientAuth class.
    ClientAuth clientAuth = new ClientAuth();
    String apiKey = clientAuth.getApiKey();
    // TODO build and return a new GET HttpRequest with an api key header.

    // Hint: .header("x-api-key", apiKey)
    return HttpRequest.newBuilder()
        .uri(URI.create(reqUri))
        .header("x-api-key",apiKey)
        .build();
  }

  /**
   * Similar to the secured GET request, but is a POST request instead.
   *
   * @param param - the body of the POST request. This should be your name, passed in from the REPL.
   * @return an HttpRequest object for accessing and posting to the secured resource.
   */
  public static HttpRequest getSecuredPostRequest(String param) {
    String reqUri = "https://runwayapi.herokuapp.com/integration";
    String apiKey;
    // TODO build and return a new POST HttpRequest with an api key header, and the param in the body.
    ClientAuth clientAuth = new ClientAuth();
    apiKey = clientAuth.getApiKey();
    return HttpRequest.newBuilder()
        .uri(URI.create(reqUri))
        .POST(HttpRequest.BodyPublishers.ofString("{\"auth\": \"m\"}"))
        .header("x-api-key","Jid9mm6")
        .build();
    // Hint: the POST param should be: HttpRequest.BodyPublishers.ofString("{\"name\":\"" + param + "\"}")
  }

  /**
   * This is another secured GET request that has an optional string parameter in the URL. Find out what the staff's
   * horoscopes are!
   *
   * @param param - the name of the staff member whose horoscope you want to find; an empty string here will indicate
   *              that the server should return a list of all staff members instead.
   * @return an HttpRequest object for accessing and posting to the secured resource.
   */
  public static HttpRequest getHoroscopeGetRequest(String param) {
    // Our taName parameter can either be empty, or some name, in which case it takes the format "?taName=name".
    // If you tried this in the web browser URL you might see something like
    // https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/securedResource?taName=theInputName
    String taName;
    // TODO set the taName. It should either be empty "" if the param is empty, or else of the format "?taName=param"
    if (param.isEmpty()) {
        taName = "";
    } else {
      taName = "?taName=" + param;
    }
    String reqUri =
        "https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/horoscopeResource/" + taName;
    // TODO get the secret API key by using the ClientAuth class.
    ClientAuth clientAuth = new ClientAuth();
    String apiKey = clientAuth.getApiKey();
    System.out.println("Getting star sign for " + param);
    // TODO build and return a new GET request with the api key header.
    return HttpRequest.newBuilder()
        .uri(URI.create(reqUri))
        .header("x-api-key", apiKey)
        .build();
  }
}
