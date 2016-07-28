import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ParametersParser
{
    private static ParametersParser instance;

    private String testConvertToParam;
    private String testCorrectNumFromParam;
    private String testCorrectNumToParam;

    private ParametersParser()
    {
        try
        {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("tests_params.xml");
            Node root = document.getDocumentElement();
            NodeList items = root.getChildNodes();
            String str = "";

            for (int i = 0; i < items.getLength(); i++)
            {
                Node item = items.item(i);

                if (item.getNodeType() != Node.TEXT_NODE)
                {
                    NodeList itemProps = item.getChildNodes();

                    for(int j = 0; j < itemProps.getLength(); j++)
                    {
                        Node itemProp = itemProps.item(j);

                        if (itemProp.getNodeType() != Node.TEXT_NODE)
                        {
                            if (itemProp.getNodeName().equals("name"))
                            {
                                str = itemProp.getChildNodes().item(0).getTextContent();
                            }
                            else
                            {
                                if (str.equals("testConvertTo"))
                                {
                                    testConvertToParam = itemProp.getChildNodes().item(0).getTextContent();
                                }
                                else if (str.equals("testCorrectNumFrom"))
                                {
                                    testCorrectNumFromParam = itemProp.getChildNodes().item(0).getTextContent();
                                }
                                else
                                {
                                    testCorrectNumToParam = itemProp.getChildNodes().item(0).getTextContent();
                                }
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public String getTestConvertToParam()
    {
        return testConvertToParam;
    }

    public String getTestCorrectNumFromParam()
    {
        return testCorrectNumFromParam;
    }

    public String getTestCorrectNumToParam()
    {
        return testCorrectNumToParam;
    }

    public static ParametersParser getInstance()
    {
        if (instance == null)
        {
            instance = new ParametersParser();
        }

        return instance;
    }
}