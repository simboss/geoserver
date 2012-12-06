/* Copyright (c) 2012 TOPP - www.openplans.org. All rights reserved.
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.geoserver.wps.raster;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import net.sf.json.JSONSerializer;

import org.apache.commons.io.IOUtils;
import org.geoserver.wps.ppio.CDataPPIO;

/**
 * @author Damiano Giampaoli, GeoSolutions SAS
 * @author Simone Giannecchini, GeoSolutions SAS
 * 
 */
public class ChangeMatrixPPIO extends CDataPPIO {
	
	

    public ChangeMatrixPPIO() {
        super(ChangeMatrixDTO.class, ChangeMatrixDTO.class, "application/json");
    }

    @Override
    public Object decode(InputStream input) throws Exception {
        return null;
    }

    @Override
    public void encode(Object value, OutputStream os) throws Exception {
        PrintWriter pw = new PrintWriter(os);
        try{

            pw.write(JSONSerializer.toJSON(((ChangeMatrixDTO)value).getChangeMatrix()).toString());
        } finally{
        	IOUtils.closeQuietly(pw);
        	IOUtils.closeQuietly(os);
        }
    }

    @Override
    public String getFileExtension() {
        return "json";
    }

    /* (non-Javadoc)
     * @see org.geoserver.wps.ppio.CDataPPIO#decode(java.lang.String)
     */
    @Override
    public Object decode(String input) throws Exception {
        return null;
    }

}
