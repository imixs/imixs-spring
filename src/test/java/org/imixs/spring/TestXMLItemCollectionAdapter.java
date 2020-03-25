package org.imixs.spring;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.xml.XMLDocument;
import org.imixs.workflow.xml.XMLDocumentAdapter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for xmlItemCollection object
 * 
 * @author rsoika
 * 
 */
public class TestXMLItemCollectionAdapter {

  /**  
   * Simple test converting an ItemCollection into XML 
   */
	@Test 
	public void testItemCollection() {
		ItemCollection itemCollection = new ItemCollection();
		itemCollection.replaceItemValue("_subject", "Hello");
		XMLDocument xmlItemCollection = null;
		try {
			xmlItemCollection = XMLDocumentAdapter.getDocument(itemCollection);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

		ItemCollection result = XMLDocumentAdapter.putDocument(xmlItemCollection);

		Assert.assertEquals(itemCollection.getItemValueString("_subject"), "Hello");

		Assert.assertEquals(result.getItemValueString("_subject"), "Hello");
	}

}
