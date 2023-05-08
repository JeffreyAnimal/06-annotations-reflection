package ohm.softa.a06;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ohm.softa.a06.model.Joke;
import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;
import java.util.*;

public class JokeAdapter extends TypeAdapter{

	@Override
	public List<Joke> read(final JsonReader reader) throws IOException {

		final List<Joke> jokeList=new ArrayList<Joke>();
		Gson gson=new Gson();

		if (reader.equals(null))
			throw new IOException("object to convert is empty");


		reader.beginObject();
		while(reader.hasNext()){

			jokeList.add(gson.fromJson(reader.nextString(),Joke.class));
		}
		reader.endObject();

		return jokeList;
	}

	@Override
	public void write(JsonWriter writer, Object inst) {
		throw new NotImplementedException("write Method not supported");
	}
}
