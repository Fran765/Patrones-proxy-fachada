package ejercicio1.dominio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class PorWebObtenerClima implements Clima {

	private String url = "https://api.openweathermap.org/data/2.5/weather?q=viedma,032&lang=sp&APPID=";

	public PorWebObtenerClima(String apiKey) {
		this.url = this.url.concat(apiKey);
	}

	public String estadoDelDia() {

		HttpClient httpClient = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		String jsonString = response.body();

		Gson gson = new GsonBuilder().create();
		var jsonObject = gson.fromJson(jsonString, JsonObject.class);

		return jsonObject.getAsJsonArray("weather").asList().get(0).getAsJsonObject().get("description").toString();
	}

}
