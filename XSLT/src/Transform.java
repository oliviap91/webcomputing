import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.*;


public class Transform {

    StreamSource xmlSource;
    StreamSource xsltSource;
    StreamResult xmlResult;


    public static void main(String[] args){

        Transform t = new Transform();
        t.setArgs(args);
        t.transformDoc();
    }

    public void setArgs(String[] args) {

        xmlSource = new StreamSource(new File(args[0]));
        xsltSource = new StreamSource(new File(args[1]));

        if (args.length > 2){
            xmlResult = new StreamResult(new File(args[2]));
        }
        else {
            xmlResult = new StreamResult(System.out);
        }

    }

    public void transformDoc(){

        try {
            TransformerFactory xformFactory
                    = TransformerFactory.newInstance();
            Transformer trans = xformFactory.newTransformer(xsltSource);
            trans.transform(xmlSource, xmlResult);
        }
        catch (TransformerFactoryConfigurationError e) {
            System.out.println("Could not locate a factory class");
        }
        catch (TransformerConfigurationException e) {
            System.out.println("This DOM does not support transforms.");
        }
        catch (TransformerException e) {
            System.out.println("Transform failed.");
        }

    }

}