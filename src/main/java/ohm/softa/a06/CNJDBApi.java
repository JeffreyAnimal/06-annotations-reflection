package ohm.softa.a06;


import ohm.softa.a06.model.Joke;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CNJDBApi {

	@GET("jokes/random")
	Call<Joke> getRandomJoke();

	Call<Joke> getRandomJoke(String[] categories);

	Call<Joke[]> getJokesBySearch(String query);

	Call<Joke> getJoke(String id);
}
