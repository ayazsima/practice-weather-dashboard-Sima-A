package com.example.weather_dashboard;

import com.example.weather_dashboard.model.WeatherResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class WeatherDashboardApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		RestTemplate restTemplate = new RestTemplate();

		String apiKey = System.getenv("OPENWEATHER_API_KEY");

		System.out.println("=== Weather Dashboard ===");
		System.out.println("1. St. Louis");
		System.out.println("2. Kansas City");
		System.out.println("3. Springfield");
		System.out.print("Choose a city: ");

		String choice = scanner.nextLine();
		scanner.close();

		String city;

		if (choice.equals("1")) {
			city = "St. Louis,MO,US";
		} else if (choice.equals("2")) {
			city = "Kansas City,MO,US";
		} else if (choice.equals("3")) {
			city = "Springfield,MO,US";
		} else {
			System.out.println("Invalid choice.");
			return;
		}

		String url = "https://api.openweathermap.org/data/2.5/weather?q="
				+ city + "&appid=" + apiKey + "&units=metric";

		try {
			WeatherResponse response = restTemplate.getForObject(
					url, WeatherResponse.class,city, apiKey
			);

			if (response == null) {
				System.out.println("No weather data found.");
				return;
			}

			double temperature = response.getMain().getTemp();
			int humidity = response.getMain().getHumidity();
			String description = response.getWeather()[0].getDescription();

			System.out.println("\nCity: " + city);
			System.out.println("Temperature: " + temperature + " °C");
			System.out.println("Conditions: " + description);
			System.out.println("Humidity: " + humidity + "%");

		} catch (RestClientException ex) {
			System.out.println("Could not get weather. Check your API key and connection.");
		}
	}

}
