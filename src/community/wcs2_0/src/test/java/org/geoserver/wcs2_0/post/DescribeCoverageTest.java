package org.geoserver.wcs2_0.post;

import static org.custommonkey.xmlunit.XMLAssert.assertXpathEvaluatesTo;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.geoserver.wcs2_0.WCSTestSupport;
import org.junit.Test;
import org.w3c.dom.Document;

public class DescribeCoverageTest extends WCSTestSupport {

    @Test
    public void testDescribeCoverageSimple() throws Exception {

        final File xml= new File("./src/test/resources/testDescribeCoverage.xml");
        final String request= FileUtils.readFileToString(xml);                
        
        Document dom = postAsDOM("wcs", request);
        assertNotNull(dom);
        // print(dom, System.out);
        
        // validate
        checkValidationErrors(dom, WCS20_SCHEMA);
        
        // check it is good
        assertXpathEvaluatesTo("wcs__BlueMarble", "//wcs:CoverageDescription//wcs:CoverageId", dom);
        assertXpathEvaluatesTo("3", "count(//wcs:CoverageDescription//gmlcov:rangeType//swe:DataRecord//swe:field)", dom);
        
    }
    
    @Test
    public void testDescribeCoverageMultiband() throws Exception {
        final File xml= new File("./src/test/resources/testDescribeCoverageMultiBand.xml");
        final String request= FileUtils.readFileToString(xml);  
        
        Document dom = postAsDOM("wcs", request);
        assertNotNull(dom);
        // print(dom, System.out);
        
        checkValidationErrors(dom, WCS20_SCHEMA);

        // check it is good
        assertXpathEvaluatesTo("wcs__multiband", "//wcs:CoverageDescription//wcs:CoverageId", dom);
        assertXpathEvaluatesTo("9", "count(//wcs:CoverageDescription//gmlcov:rangeType//swe:DataRecord//swe:field)", dom);
    }
}
