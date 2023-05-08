package ohm.softa.a06.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ohm.softa.a06.CNJDBApi;
import ohm.softa.a06.JokeAdapter;
import ohm.softa.a06.model.Joke;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
class CNJDBTests {

	private static final Logger logger = LogManager.getLogger(CNJDBTests.class);
	private static final int REQUEST_COUNT = 10;



	@Test
	void testCollision() throws IOException {
		Set<String> jokeNumbers = new HashSet<>();
		int requests = 0;
		boolean collision = false;

//		Gson gson=new GsonBuilder()
//			.registerTypeAdapter(Joke[].class,new JokeAdapter())
//			.create();

		Retrofit retrofit=new Retrofit.Builder()
			.baseUrl("https://api.chucknorris.io/")
			.addConverterFactory(GsonConverterFactory.create())
			.build();

		while (requests++ < REQUEST_COUNT) {

			// TODO Prepare call object

			CNJDBApi cnjdb=retrofit.create(CNJDBApi.class);

			// TODO Perform a synchronous request
			Call<Joke> jokeCall=cnjdb.getRandomJoke();

			logger.info("recieved joke from Server: %s",jokeCall);

			// TODO Extract object
			Response<Joke> jokeResponse=jokeCall.execute();

			if(!jokeResponse.isSuccessful()) continue;




			Joke j = jokeResponse.body();
			logger.info(j.getContent());

			if (jokeNumbers.contains(j.getIdentifier())) {
				logger.info(String.format("Collision at joke %s", j.getIdentifier()));
				collision = true;
				break;
			}

			jokeNumbers.add(j.getIdentifier());
			logger.info(j.toString());
		}

		assertTrue(collision, String.format("Completed %d requests without collision; consider increasing REQUEST_COUNT", requests));
	}
}
