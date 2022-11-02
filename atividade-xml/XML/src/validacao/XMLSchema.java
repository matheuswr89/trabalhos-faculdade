package validacao;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XMLSchema {
	public static void main(String args[]) throws IOException {
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Source schemaFile = new StreamSource(new File("pessoa.xsd"));
			Schema schema = factory.newSchema(schemaFile);

			Validator validator = schema.newValidator();

			validator.validate(new StreamSource(new File("pessoa.xml")));
			System.out.println("Arquivo válido");
		} catch (SAXException e) {
			System.out.println("Arquivo inválido");
		}

	}
}