/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import panel.InitialPanel;

/**
 *
 * @author sluja
 */
public class FilesHandler {
    
    private String source = "";
    private URL url;
    private BufferedReader reader;
    private Writer writer;
    private boolean fileFound = true;
    private String temp = "", tempRead = "";
    private int counter = 0, settingCounter = 3;
    FileReader fReader;
    
    //FileInputStream fIn = ...;
    
    private File originalFile;
    
    public void setURL(String source){
        this.source = source;
        url = this.getClass().getClassLoader().getResource(this.source);
        
       // BufferedReader bRead = new BufferedReader(new InputStreamReader(fIn));
        setBufferedReader();
    }
    
    private void setBufferedReader()
    {
        try {
            fReader = new FileReader(url.getFile());
            reader = new BufferedReader(fReader);
        } catch (FileNotFoundException ex) {
            JOptionPane.showConfirmDialog(null, "Config not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            fileFound = false;
        }
    }
    
    private void setBufferedWriter(File file)
    {
        try{
             writer = new BufferedWriter(new FileWriter(file));
        }catch(IOException ex1)
        {
            JOptionPane.showConfirmDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public BufferedReader getReader()
    {
        return this.reader;
    }
    
    public Writer getWriter()
    {
        return this.writer;
    }
    
  /*  public String getSettings()
    {
        
        Color color;
        counter = 0;
        String conf;
        if(fileFound){
            try{
                while(counter < settingCounter)
                {
                  temp = getReader().readLine();  
                    System.out.println("lolo: " + temp);
                  switch(counter){
                      case 0: 
                      {
                         conf = temp.replace("bColor:", "");
                         if(!conf.contains("UIManager")) color = new Color(Integer.parseInt(conf), true);
                         else color = UIManager.getColor("Panel.background");
                         initPanel.setColorConfig(color);
                         break;
                      }
                      case 1:
                      {
                          conf = temp.replace("tExist:", "");
                          conf = conf.replace(";", "");
                          initPanel.setTableExist(Boolean.valueOf(conf));
                          break;
                      }
                      case 2:
                      {
                         conf = temp.replace("pTExist:", "");
                         conf = conf.replace(";", "");
                         initPanel.setProdTableExist(Boolean.valueOf(conf));
                      }
                  }
                 
                  counter += 1;
                }
                getReader().close();
            }catch(IOException ex){
                JOptionPane.showConfirmDialog(null, "Line not found", "ERROR", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
        
        return "";
    }*/
    
    private void setOriginalFile()
    {
        originalFile = new File(url.getFile());
    }
    
    public File getOriginalFile()
    {
        return this.originalFile;
    }
    
    private void saveTempFile(File tempFile)
    {
        setOriginalFile();
        try{
        FileChannel src = new FileInputStream(tempFile).getChannel();
        FileChannel dest = new FileOutputStream(getOriginalFile()).getChannel();
        dest.transferFrom(src, 0, src.size());
        }catch(FileNotFoundException ex)
        {
            
        }catch(IOException ex1)
        {
            
        }
    }
    
    public void saveSettings(String key, String newValue, ResourceBundle resource)
    {
       // resource.
        /*File tempFile;
        File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
        counter = 0;
        try {
            setURL(this.source);
            tempFile = File.createTempFile("temp", ".tmp",tempDirectory);
            // Delete temp file when program exits.
            tempFile.deleteOnExit();
            this.setBufferedWriter(tempFile);
            
            while((tempRead = getReader().readLine()) != null)
            {
                if(counter == setting) 
                {    
                 this.getWriter().write(text + "\n");
                 counter += 1;
                 continue;
                }
                this.getWriter().write(tempRead + "\n");
                System.out.println("C: " + counter);
                counter += 1;
                
            }
            
            writer.close();
            getReader().close();
            this.saveTempFile(tempFile);
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }*/
        
    }
    
    public void closeFileHandler()
    {
        
    }
}
