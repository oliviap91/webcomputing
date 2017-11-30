/**
 * Created by olivi on 28/11/2017.
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

public class XMLMerge {

    Document doc1;
    Document doc2;
    Document doc3;

    public static void main(String[] args){
        XMLMerge merger = new XMLMerge();
        merger.readDoc(args[0], args[1]);
        merger.processDoc();
        merger.writeDoc(args[2]);
    }

    public void readDoc(String filename1, String filename2) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc1 = builder.parse( new File(filename1) );
            doc2 = builder.parse( new File(filename2) );
            doc3 = builder.newDocument();
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
        (new SerializeHack(doc3, new File(that))).write();
    }

    public void processDoc(){
        mergeDocument();
    }

    private void mergeDocument() {
        Element root1 = doc1.getDocumentElement();
        Element root2 = doc2.getDocumentElement();

        Element newRoot1 = (Element) doc3.importNode(root1,true);
        Element newRoot2 = (Element) doc3.importNode(root2,true);

        String rootTag1 = newRoot1.getTagName();
        String rootTag2 = newRoot2.getTagName();

        Element newRoot = doc3.createElement(rootTag1+"-"+rootTag2);

        newRoot.appendChild( doc3.createTextNode("\n") );
        newRoot.appendChild(newRoot1);
        newRoot.appendChild(newRoot2);
        newRoot.appendChild( doc3.createTextNode("\n") );

        doc3.appendChild(newRoot);
    }
}
