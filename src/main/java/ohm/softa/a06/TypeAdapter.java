package ohm.softa.a06;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public abstract class TypeAdapter<T> {

	public abstract T read(final JsonReader reader) throws IOException;

	public abstract void write(final JsonWriter writer, final T inst);
}
