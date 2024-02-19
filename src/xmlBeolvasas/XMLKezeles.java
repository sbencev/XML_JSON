package xmlBeolvasas;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLKezeles {

	public List<Auto> xmlBeolvasas(String fajlnev) {

		// File fajl = new File(fajlnev);
		List<Auto> autok = new ArrayList<Auto>();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			DocumentBuilder docBuild = dbf.newDocumentBuilder();

			Document doksi = docBuild.parse(fajlnev);

			doksi.getDocumentElement().normalize();

			System.out.println(doksi.getDocumentElement().getNodeName());

			NodeList nodeLista = doksi.getElementsByTagName("auto");

			for (int i = 0; i < nodeLista.getLength(); i++) {

				Node node = nodeLista.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element elem = (Element) node;

					autok.add(new Auto(elem.getElementsByTagName("gyartmany").item(0).getTextContent(),
							elem.getElementsByTagName("tipus").item(0).getTextContent(),
							Integer.parseInt(elem.getElementsByTagName("gyartasiev").item(0).getTextContent()),
							Integer.parseInt(elem.getElementsByTagName("ar").item(0).getTextContent())));

				}

			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

		return autok;

	}

	public void xmlKeszit(String fajlnev, List<Auto> autok) throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();
		Document doksi = docBuilder.newDocument();

		Element gyokerElem = doksi.createElement("autokereskedes");

		doksi.appendChild(gyokerElem);

		Element autoElem, adatElem;
		for (Auto auto : autok) {

			autoElem = doksi.createElement("auto");
			gyokerElem.appendChild(autoElem);

			adatElem = doksi.createElement("gyartmany");
			adatElem.appendChild(doksi.createTextNode(auto.getGyartmany()));
			autoElem.appendChild(adatElem);

			adatElem = doksi.createElement("tipus");
			adatElem.appendChild(doksi.createTextNode(auto.getTipus()));
			autoElem.appendChild(adatElem);

			adatElem = doksi.createElement("gyartasiev");
			adatElem.appendChild(doksi.createTextNode(String.valueOf(auto.getGyartasiEv())));
			autoElem.appendChild(adatElem);

			adatElem = doksi.createElement("ar");
			adatElem.appendChild(doksi.createTextNode(String.valueOf(auto.getAr())));
			autoElem.appendChild(adatElem);

		}

		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer trans = tff.newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		DOMSource domForras = new DOMSource(doksi);
		StreamResult result = new StreamResult(fajlnev);
		trans.transform(domForras, result);

	}

}
