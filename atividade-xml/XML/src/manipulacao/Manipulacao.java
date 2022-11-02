package manipulacao;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Manipulacao {
	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		try {
			File file = new File("biblioteca.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = (Document) db.parse(file);
			document.getDocumentElement().normalize();
			NodeList nList = document.getElementsByTagName("endereco");
			Element element = (Element) nList.item(0);
			System.out.println("CEP: " + element.getAttribute("cep"));
			NodeList nList2 = document.getElementsByTagName("livro");
			System.out.println("----------------------------");
			for (int temp = 0; temp < nList2.getLength(); temp++) {
				Node nNode = nList2.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Quantidade: " + eElement.getAttribute("quantidade"));
					System.out.println("Emprestados: " + eElement.getAttribute("emprestados"));
					System.out.println("Titulo: " + eElement.getElementsByTagName("titulo").item(0).getTextContent());
					System.out.println(
							"Total de Paginas: " + eElement.getElementsByTagName("paginas").item(0).getTextContent());
					System.out.println(
							"Autor: " + eElement.getElementsByTagName("autor").item(0).getTextContent() + "\n\n");
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
