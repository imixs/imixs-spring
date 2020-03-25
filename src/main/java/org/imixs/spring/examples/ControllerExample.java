package org.imixs.spring.examples;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.xml.XMLDocument;
import org.imixs.workflow.xml.XMLDocumentAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * This is an example Spring rest service consuming and producing Imixs XMLDocument Data objects.
 * 
 * @author rsoika
 *
 */
@Controller
@RequestMapping("/adapter")
public class ControllerExample {


  /** 
   * GET Example
   * <p>
   * Creates a new document with the item '_name' and returns a XMLDocument data structure.
   * 
   * @param name
   * @return
   */
  @RequestMapping(value = "{name}", method = RequestMethod.GET)
  public @ResponseBody XMLDocument getExampleInXML(@PathVariable String name) {

    ItemCollection document = new ItemCollection();
    document.setItemValue("_name", name);

    XMLDocument xmlItemCollection = XMLDocumentAdapter.getDocument(document);
    return xmlItemCollection;

  }

  /**
   * GET Example
   * <p>
   * Creates a new document with the item '_name' and returns a XMLDocument data structure.
   * 
   * @param name
   * @return
   */
  @RequestMapping(value = "{name}", method = RequestMethod.GET)
  public ResponseEntity<?> getExampleResponseEntity(@PathVariable String name) {

    ItemCollection document = new ItemCollection();
    document.setItemValue("_name", name);

    // convert resultData into xml
    return ResponseEntity.ok().body(XMLDocumentAdapter.getDocument(document));

  }


  /**
   * POST Example
   * <p>
   * request/response XMLDocument data
   * 
   * @param request
   * @return
   */
  @PostMapping("data")
  public ResponseEntity<?> getData(@RequestBody XMLDocument requestXML) {
    // consume xml request
    ItemCollection requestData = XMLDocumentAdapter.putDocument(requestXML);
    // consume data....
    @SuppressWarnings("unused")
    String param1 = requestData.getItemValue("param1", String.class);


    // create response data object
    ItemCollection resultData = new ItemCollection();
    resultData.setItemValue("_subject", "some data...");

    // convert resultData into xml
    return ResponseEntity.ok().body(XMLDocumentAdapter.getDocument(resultData));

  }
}
