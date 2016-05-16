/**
 * Created by SerP on 16.05.2016.
 */
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


public class testJdomXml {
    public static void main(String[] args) throws IOException {
        Element carElement = new Element("car");
        Document myDocument = new Document(carElement);
        carElement.setAttribute(new Attribute("vin", "123fhg5869705iop90"));

        Element make = new Element("make");
        make.addContent("Toyota");
        carElement.addContent(make);

        carElement.addContent(new Element("make").addContent("Toyota"));

        carElement.addContent(new Element("model").addContent("Celica"));
        carElement.addContent(new Element("year").addContent("1997"));
        carElement.addContent(new Element("color").addContent("green"));
        carElement.addContent(new Element("license")
                .addContent("1ABC234").setAttribute("state", "CA"));

        carElement.addContent(new Comment("Description of a car"));

        Element yearElement = carElement.getChild("year");

        boolean removed = carElement.removeChild("year");

        XMLOutputter outputter1 = new XMLOutputter();
        outputter1.output(myDocument, new FileOutputStream("foo2.xml"));

        XMLOutputter outputter = null;
        try {
            outputter = new XMLOutputter();
            outputter.output(myDocument, System.out);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        FileWriter writer = new FileWriter("myFile.xml");
        outputter.output(myDocument, writer);
        writer.close();

        try {
            SAXBuilder builder = new SAXBuilder();
            Document anotherDocument =
                    builder.build(new File("myFile.xml"));
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
