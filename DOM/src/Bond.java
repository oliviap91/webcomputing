import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by olivi on 30/11/2017.
 */
public class Bond {
    Document doc1;

    public static void main(String[] args){
        Bond films = new Bond();
        films.readDoc(args[0]);
        films.processDoc();
        films.writeDoc(args[1]);
    }

    public void readDoc(String filename1) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc1 = builder.parse( new File(filename1) );
        }
        catch (FactoryConfigurationError fce){
            System.err.println("Could not create DocumentBuilderFactory");
        }
        catch (ParserConfigurationException pce) {
            System.out.println("Could not locate a JAXP parser");
        }
        catch (SAXException se) {
            System.out.println("XML file is not well-formed.");
        }
        catch (IOException ioe) {
            System.out.println(
                    "Due to an IOException, the parser could not read the XML file"
            );
        }
    }

    public void writeDoc(String that){
        (new SerializeHack(doc1, new File(that))).write();
    }

    public void processDoc(){
        NodeList movies = doc1.getElementsByTagName("movie");
        for (int i=0; i < movies.getLength(); i++){
            ((Element) movies.item(i)).removeAttribute("type");
        }
    }
}
