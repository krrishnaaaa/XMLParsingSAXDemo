package pcsalt.example.xmlparsingsaxdemo;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLHelper extends DefaultHandler {
    /**
     * The URL to be parsed
     */
    private final String URL_MAIN = "https://pcsalt.com/sample.xml";
    private final String TAG = "XMLHelper";
    private final ArrayList<BookDetails> bookDetailsArrayList = new ArrayList<>();
    private Boolean currTag = false;
    private String currTagVal = "";
    private BookDetails bookDetails = null;

    public ArrayList<BookDetails> getPostsList() {
        return this.bookDetailsArrayList;
    }

    /**
     * Method to read XML from {@link pcsalt.example.xmlparsingsaxdemo.XMLHelper#URL_MAIN}
     */
    public void get() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser mSaxParser = factory.newSAXParser();
            XMLReader mXmlReader = mSaxParser.getXMLReader();
            mXmlReader.setContentHandler(this);
            InputStream mInputStream = new URL(URL_MAIN).openStream();
            mXmlReader.parse(new InputSource(mInputStream));
        } catch (Exception e) {
            // Exceptions can be handled for different types
            // But, this is about XML Parsing not about Exception Handling
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    // This method receives notification character data inside an element
    // e.g. <post_title>Add EditText Pragmatically - Android</post_title>
    // It will be called to read "Add EditText Pragmatically - Android"
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (currTag) {
            currTagVal = currTagVal + new String(ch, start, length);
            currTag = false;
        }
    }

    // Receives notification of end of element
    // e.g. <post_title>Add EditText Pragmatically - Android</post_title>
    // It will be called when </post_title> is encountered
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        currTag = false;

        if (localName.equalsIgnoreCase("author"))
            bookDetails.setAuthor(currTagVal);

        else if (localName.equalsIgnoreCase("title"))
            bookDetails.setTitle(currTagVal);

        else if (localName.equalsIgnoreCase("genre"))
            bookDetails.setGenre(currTagVal);

        else if (localName.equalsIgnoreCase("publish_date"))
            bookDetails.setPublishDate(currTagVal);

        else if (localName.equalsIgnoreCase("description"))
            bookDetails.setDescription(currTagVal);

        else if (localName.equalsIgnoreCase("book"))
            bookDetailsArrayList.add(bookDetails);
    }

    // Receives notification of start of an element
    // e.g. <post_title>Add EditText Pragmatically - Android</post_title>
    // It will be called when <post_title> is encountered
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        Log.i(TAG, "TAG: " + localName);

        currTag = true;
        currTagVal = "";
        // Whenever <post> element is encountered it will create new object of BookDetails
        if (localName.equals("book"))
            bookDetails = new BookDetails();
    }
}
