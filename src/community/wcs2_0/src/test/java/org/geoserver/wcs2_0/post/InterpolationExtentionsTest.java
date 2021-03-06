package org.geoserver.wcs2_0.post;

import static junit.framework.Assert.assertEquals;
import it.geosolutions.imageioimpl.plugins.tiff.TIFFImageReader;
import it.geosolutions.imageioimpl.plugins.tiff.TIFFImageReaderSpi;

import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.io.FileUtils;
import org.geoserver.wcs2_0.WCSTestSupport;
import org.junit.Test;
import org.w3c.dom.Document;

import com.mockrunner.mock.web.MockHttpServletResponse;
/**
 * 
 * @author Simone Giannecchini, GeoSolutions
 *
 */
public class InterpolationExtentionsTest extends WCSTestSupport {

    @Test
    public void testInterpolationSingleLinearXML() throws Exception {
        
        final File xml= new File("./src/test/resources/requestGetCoverageInterpolationLinear.xml");
        final String request= FileUtils.readFileToString(xml);
        MockHttpServletResponse response = postAsServletResponse("wcs", request);

        assertEquals("image/tiff", response.getContentType());
        byte[] tiffContents = getBinary(response);
        File file = File.createTempFile("bm_gtiff", "bm_gtiff.tiff", new File("./target"));
        FileUtils.writeByteArrayToFile(file, tiffContents);

        // TODO: check the tiff structure is the one requested
        final TIFFImageReader reader = (TIFFImageReader) new TIFFImageReaderSpi()
                .createReaderInstance();
        reader.setInput(new FileImageInputStream(file)); 
        assertEquals(360, reader.getWidth(0));
        assertEquals(360, reader.getHeight(0));
        reader.dispose();           
    }

    @Test
    public void testInterpolationSingleNearestXML() throws Exception {
        
        final File xml= new File("./src/test/resources/requestGetCoverageInterpolationNearest.xml");
        final String request= FileUtils.readFileToString(xml);
        MockHttpServletResponse response = postAsServletResponse("wcs", request);

        assertEquals("image/tiff", response.getContentType());
        byte[] tiffContents = getBinary(response);
        File file = File.createTempFile("bm_gtiff", "bm_gtiff.tiff", new File("./target"));
        FileUtils.writeByteArrayToFile(file, tiffContents);

        // TODO: check the tiff structure is the one requested
        final TIFFImageReader reader = (TIFFImageReader) new TIFFImageReaderSpi()
                .createReaderInstance();
        reader.setInput(new FileImageInputStream(file)); 
        assertEquals(360, reader.getWidth(0));
        assertEquals(360, reader.getHeight(0));
        reader.dispose();           
    }
    
    @Test
    public void testInterpolationMixedSupportedXML() throws Exception {
        
        final File xml= new File("./src/test/resources/requestGetCoverageInterpolationMixedSupported.xml");
        final String request= FileUtils.readFileToString(xml);
        MockHttpServletResponse response = postAsServletResponse("wcs", request);
    
        assertEquals("image/tiff", response.getContentType());
        byte[] tiffContents = getBinary(response);
        File file = File.createTempFile("bm_gtiff", "bm_gtiff.tiff", new File("./target"));
        FileUtils.writeByteArrayToFile(file, tiffContents);

        // TODO: check the tiff structure is the one requested
        final TIFFImageReader reader = (TIFFImageReader) new TIFFImageReaderSpi()
                .createReaderInstance();
        reader.setInput(new FileImageInputStream(file)); 
        assertEquals(360, reader.getWidth(0));
        assertEquals(360, reader.getHeight(0));
        reader.dispose();       
    }

    @Test
    public void testInterpolationMixedTimeXML() throws Exception {
        
        final File xml= new File("./src/test/resources/requestGetCoverageInterpolationMixedTime.xml");
        final String request= FileUtils.readFileToString(xml);
        MockHttpServletResponse response = postAsServletResponse("wcs", request);
    
        assertEquals("application/xml", response.getContentType());
        Document dom = dom(new ByteArrayInputStream(response.getOutputStreamContent().getBytes()));
        print(dom);       
    }

    @Test
    public void testInterpolationMixedUnsupportedXML() throws Exception {
        
        final File xml= new File("./src/test/resources/requestGetCoverageInterpolationMixedUnsupported.xml");
        final String request= FileUtils.readFileToString(xml);
        MockHttpServletResponse response = postAsServletResponse("wcs", request);
    
        assertEquals("application/xml", response.getContentType());
        Document dom = dom(new ByteArrayInputStream(response.getOutputStreamContent().getBytes()));
        print(dom);       
    }

    @Test
    public void testInterpolationMixedDuplicatedXML() throws Exception {
        
        final File xml= new File("./src/test/resources/requestGetCoverageInterpolationMixedDuplicated.xml");
        final String request= FileUtils.readFileToString(xml);
        MockHttpServletResponse response = postAsServletResponse("wcs", request);
    
        assertEquals("application/xml", response.getContentType());
        Document dom = dom(new ByteArrayInputStream(response.getOutputStreamContent().getBytes()));
        print(dom);        
    }
    

}
