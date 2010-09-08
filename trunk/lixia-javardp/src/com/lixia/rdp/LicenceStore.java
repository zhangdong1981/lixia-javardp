/* LicenceStore.java
 * Component: ProperJavaRDP
 * 
 * Revision: $Revision: 1.1.1.1 $
 * Author: $Author: suvarov $
 * Date: $Date: 2007/03/08 00:26:35 $
 *
 * Copyright (c) 2005 Propero Limited
 *
 * Purpose: Handle saving and loading of licences
 */
package com.lixia.rdp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

public abstract class LicenceStore {
    
    static Logger logger = Logger.getLogger(Licence.class);

    /**
     * Load a licence from a file
     * @return Licence data stored in file
     */
    public byte[] load_licence(){
        String path = Options.licence_path + "/licence."+Options.hostname;
        byte[] data = null;
        try{
            FileInputStream fd = new FileInputStream(path);
            data = new byte[fd.available()];
            fd.read(data);
        }
        catch(FileNotFoundException e){logger.warn("Licence file not found!");}
        catch(IOException e){logger.warn("IOException in load_licence");}
        return data;
    }

    /**
     * Save a licence to file
     * @param databytes Licence data to store
     */
    public void save_licence(byte[] databytes){
        /* set and create the directory -- if it doesn't exist. */
        //String home = "/root"; 
        String dirpath = Options.licence_path;//home+"/.rdesktop";
        String filepath = dirpath +"/licence."+Options.hostname;
        
        File file = new File(dirpath);
        file.mkdir();
        try{
        FileOutputStream fd = new FileOutputStream(filepath);

        /* write to the licence file */
        fd.write(databytes);
        fd.close();
        logger.info("Stored licence at " + filepath);
        }
        catch(FileNotFoundException e){logger.warn("save_licence: file path not valid!");}
        catch(IOException e){logger.warn("IOException in save_licence");}
    }
    
}
