/**
 * Created by olivia on 28/11/2017.
 */
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.*;

public class xml {
    Document doc;

    public static void main(String[] args){
        xml reverse= new xml();
        reverse.readDoc(args[0]);
        reverse.processDoc();
        reverse.writeDoc(args[1]);
    }

    public void readDoc(String filename) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse( new File(filename) );
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
        (new SerializeHack(doc, new File(that))).write();
    }

    public void processDoc(){
        Node root = doc.getDocumentElement();
        reverseDocument(root);
    }

    public void reverseDocument(Node parent){
        NodeList children = parent.getChildNodes();
        Integer num = children.getLength();
        for(int i=num-1;i>-1;i--){
            Node item = children.item(i);
            reverseDocument(item);
            parent.removeChild(item);
            parent.appendChild(item);
        }
    }
}
