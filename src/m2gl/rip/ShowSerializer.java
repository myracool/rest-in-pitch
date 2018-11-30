package m2gl.rip;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ShowSerializer extends StdSerializer<Show> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

	protected ShowSerializer(Class<Show> t) {
		super(t);
	}

	@Override
	public void serialize(Show value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonGenerationException {
		jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("name", value.getName());
        jgen.writeObjectField("genres", value.getGenres());
        String formattedDate = dateFormat.format(value.getPremiere());
        jgen.writeStringField("premiere", formattedDate);
        jgen.writeStringField("offsite", value.getOffsite());
        jgen.writeNumberField("rating", value.getRating());
        jgen.writeStringField("network", value.getNetwork());
        jgen.writeStringField("country", value.getCountry());
        jgen.writeStringField("image", value.getImage());
        jgen.writeStringField("summary", value.getSummary());
        jgen.writeEndObject();
	}

}
