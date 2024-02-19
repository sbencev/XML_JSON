package xmlBeolvasas;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class XMLFoprogram {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {

		XMLKezeles xmlObj = new XMLKezeles();
		List<Auto> autok = xmlObj.xmlBeolvasas("autokereskedesUJ.xml");

		for (Auto auto : autok) {

			System.out.println(auto.toString());

		}
		
		autok.add(new Auto("Skoda", "Fabia", 2003, 950000));
		
		System.out.println("Adatok fajlba irasa");
		xmlObj.xmlKeszit("autokereskedesUJ.xml", autok);

	}

}
