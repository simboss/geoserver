/* Copyright (c) 2001 - 2013 OpenPlans - www.openplans.org. All rights reserved.
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.geoserver.wms.worldwind;

import java.io.File;
import java.io.IOException;

import junit.framework.Test;

import org.apache.commons.io.FileUtils;
import org.geoserver.wms.map.RenderedImageMapOutputFormat;
import org.geoserver.wms.map.RenderedImageMapOutputFormatTest;
import org.junit.Before;

public class DDSMapProducerTest extends RenderedImageMapOutputFormatTest {

	private String mapFormat = "image/dds";
	protected RenderedImageMapOutputFormat rasterMapProducer;

    protected RenderedImageMapOutputFormat getProducerInstance() {
        return new RenderedImageMapOutputFormat(this.mapFormat, getWMS());
    }

    @Before
    public void setUpInternal() throws Exception {
	    this.rasterMapProducer = this.getProducerInstance();
	}
    
	public String getMapFormat()
	{
		return this.mapFormat;
	}

	protected void copySchemaFile(String file) throws IOException {
        File f = new File("../../web/app/src/main/webapp/schemas/" + file);
        FileUtils.copyFile(f, getResourceLoader().createFile("WEB-INF/schemas/"+file));
    }
}
